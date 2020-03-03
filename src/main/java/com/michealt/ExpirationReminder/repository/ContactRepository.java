package com.michealt.ExpirationReminder.repository;

import com.michealt.ExpirationReminder.model.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

    Contact findByContactName(String contactName);
    Contact findByContactId(int contactId);
}
