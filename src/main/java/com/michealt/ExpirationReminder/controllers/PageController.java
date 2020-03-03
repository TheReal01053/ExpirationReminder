package com.michealt.ExpirationReminder.controllers;

import com.michealt.ExpirationReminder.model.Contact;
import com.michealt.ExpirationReminder.model.License;
import com.michealt.ExpirationReminder.repository.ContactRepository;
import com.michealt.ExpirationReminder.repository.LicenseRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private LicenseRepository licenseRepository;

    private Iterable<Contact> getContacts() {
        return contactRepository.findAll();
    }

    private Iterable<License> getLicense() {
        return licenseRepository.findAll();
    }

    @RequestMapping({"/", "", "/dashboard", ""})
    public String toDashboard(Model model) {
        return "dashboard";
    }

    @RequestMapping(value="/contact", method = RequestMethod.GET)
    public String toContact(Model model) {
        return "contact";
    }

    @RequestMapping(value="/license", method = RequestMethod.GET)
    public String toLicense(Model model) {
        model.addAttribute("contact", getContacts());
        return "license";
    }
}
