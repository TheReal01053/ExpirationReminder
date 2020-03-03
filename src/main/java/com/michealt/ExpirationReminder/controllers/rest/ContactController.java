package com.michealt.ExpirationReminder.controllers.rest;

import com.michealt.ExpirationReminder.model.Contact;
import com.michealt.ExpirationReminder.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping(value = "/deleteContactById", method = RequestMethod.POST)
    public void deleteContactById(@RequestParam("contactId") String contactId) {
        contactRepository.deleteById(Integer.parseInt(contactId));
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Iterable<Contact> findAll() {
        return contactRepository.findAll();
    }

    @RequestMapping(value="/new", method = RequestMethod.POST)
    public void addContact(@RequestParam("contactName") String contactName, @RequestParam("contactEmail") String contactEmail, @RequestParam("contactPhone") String contactPhone) {
        if (contactName.isEmpty() || contactEmail.isEmpty()) {
            System.out.println("Could not add contact did not meet conditions");
            return;
        }
        Contact contact = new Contact(contactName, contactEmail, contactPhone);
        contactRepository.save(contact);
    }
}
