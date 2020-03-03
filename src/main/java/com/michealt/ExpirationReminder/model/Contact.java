package com.michealt.ExpirationReminder.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "contact")
public @Data class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int contactId;
    @Column(name = "contactName", nullable = false)
    public String contactName;
    @Column(name = "contactEmail", nullable = false)
    public String contactEmail;
    @Column(name = "contactPhone", nullable = false)
    public String contactPhone;

    public Contact(String contactName, String contactEmail, String contactPhone) {
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public Contact() {
        super();
    }
}
