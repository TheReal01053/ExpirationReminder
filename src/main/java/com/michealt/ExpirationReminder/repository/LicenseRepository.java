package com.michealt.ExpirationReminder.repository;

import com.michealt.ExpirationReminder.model.License;
import org.springframework.data.repository.CrudRepository;

public interface LicenseRepository extends CrudRepository<License, Integer> {

    License findByLicenseName(String licenseName);
}
