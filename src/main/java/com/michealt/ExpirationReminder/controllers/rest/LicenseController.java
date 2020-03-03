package com.michealt.ExpirationReminder.controllers.rest;

import com.michealt.ExpirationReminder.model.Contact;
import com.michealt.ExpirationReminder.model.License;
import com.michealt.ExpirationReminder.repository.ContactRepository;
import com.michealt.ExpirationReminder.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

@RestController
@RequestMapping("/api/license")
public class LicenseController {

    @Autowired
    private LicenseRepository licenseRepository;
    @Autowired
    private ContactRepository contactRepository;

    private LocalDate date;

    @RequestMapping(value = "/deleteLicenseById", method = RequestMethod.POST)
    public void deleteLicenseById(@RequestParam("licenseId") String licenseId) {
        licenseRepository.deleteById(Integer.parseInt(licenseId));
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Iterable<License> findAll() {
        return licenseRepository.findAll();
    }

    private String contactEmails = "";
    private String contactName = "";

    private License license;
    @RequestMapping(value="/new", method = RequestMethod.POST)
    public void addLicense(@RequestParam("contactsId") String contactsId, @RequestParam("licenseSerial") String licenseSerial, @RequestParam("clientName") String clientName, @RequestParam("licenseName") String licenseName, @RequestParam("vendorName") String vendorName, @RequestParam("renewalDate") String renewalDate) throws ParseException {
        contactEmails = "";
        contactName = "";
        String[] contacts = contactsId.split(",");

        List<Contact> contactList = new ArrayList<>();
        Arrays.asList(contacts).stream().forEach(i -> contactList.add(contactRepository.findByContactId(Integer.parseInt(i))));

        contactList.stream().forEach(i -> {
            contactEmails += i.getContactEmail() + ", ".replace("null", "");
            contactName += i.getContactName() + ", ".replace("null", "");
        });

        LocalDate date = LocalDate.parse(renewalDate);

        int days = (int) DAYS.between(LocalDate.now(), date);
        String displayedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        license = new License("Active", contactName.substring(0, contactName.length() - 2), contactEmails.substring(0, contactEmails.length() - 2), vendorName, licenseName, licenseSerial, clientName, date, days, displayedDate);

        licenseRepository.save(license);
    }
}
