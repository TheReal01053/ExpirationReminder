package com.michealt.ExpirationReminder.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "license")
public @Data class License {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "licenseId", length = 8, columnDefinition = "integer")
    public int licenseId;
    @Column(name="licenseStatus", nullable = false)
    public String licenseStatus;
    @Column(name="licenseContact", nullable = false)
    public String licenseContact;
    @Column(name="contactEmail", nullable = false)
    public String contactEmail;
    @Column(name="licenseName", nullable = false)
    public String licenseName;
    @Column(name="licenseSerial", nullable = false)
    public String licenseSerial;
    @Column(name="clientName", nullable = false)
    public String clientName;
    @Column(name="vendorName", nullable = false)
    public String vendorName;
    @Column(name="renewalDate", nullable = false)
    private LocalDate renewalDate;
    @Column(name="daysRemaining", nullable = false)
    private int daysRemaining;
    @Column(name="displayedDate", nullable = false)
    private String displayedDate;

    public License(String licenseStatus, String licenseContact, String contactEmail, String vendorName, String licenseName, String licenseSerial, String clientName, LocalDate renewalDate, int daysRemaining, String displayedDate) {
        this.licenseStatus = licenseStatus;
        this.licenseContact = licenseContact;
        this.contactEmail = contactEmail;
        this.vendorName = vendorName;
        this.licenseName = licenseName;
        this.licenseSerial = licenseSerial;
        this.clientName = clientName;
        this.renewalDate = renewalDate;
        this.daysRemaining = daysRemaining;
        this.displayedDate = displayedDate;
    }

    public License() {
        super();
    }
}
