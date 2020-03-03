package com.michealt.ExpirationReminder.controllers.task;

import com.michealt.ExpirationReminder.mail.Send;
import com.michealt.ExpirationReminder.model.License;
import com.michealt.ExpirationReminder.repository.ContactRepository;
import com.michealt.ExpirationReminder.repository.LicenseRepository;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Configuration
@EnableScheduling
public class ScheduledTask {

    @Autowired
    LicenseRepository licenseRepository;

    @Autowired
    ContactRepository contactRepository;

    private Iterable<License> getLicenses() {
        return licenseRepository.findAll();
    }
    //cron exp second, minute, hour, day of month, month, days of week
    @Scheduled(cron = "0 0 12 * * *", zone="Australia/Brisbane") //schedule every day at 12pm BNE time
    @SchedulerLock(name = "scheduledTaskName", lockAtMostFor = "5m", lockAtLeastFor = "3m")
    //@Scheduled(fixedDelayString = "PT1M")
    public void scheduledTask() {
        System.out.println("Task started..");
        getLicenses().forEach(i -> {
            int days = (int) DAYS.between(LocalDate.now(), i.getRenewalDate());
            try {
                if (days == 0) {
                    i.setLicenseStatus("Expired");
                    licenseRepository.save(i);
                    System.out.println("Expired...");
                }
                if (days == 60 || days == 30 || days == 14 || days == 7 || days >= 0 && days <= 5) {
                    Send.SendMail(i, days);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
