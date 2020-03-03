package com.michealt.ExpirationReminder.controllers.rest;

import com.michealt.ExpirationReminder.model.License;
import com.michealt.ExpirationReminder.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(value="/api/dashboard")
public class DashboardController {

    @Autowired
    private LicenseRepository licenseRepository;

    @RequestMapping(value = "/deleteLicenseById", method = RequestMethod.POST)
    public void deleteLicenseById(@RequestParam("contactId") String contactId) {
        System.out.println(contactId);
        licenseRepository.deleteById(Integer.parseInt(contactId));
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Iterable<License> findAll() {
        return licenseRepository.findAll();
    }
}
