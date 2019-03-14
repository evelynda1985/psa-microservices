package org.parentsstepahead.psa.services;

import org.parentsstepahead.psa.models.Country;

import java.util.List;

public interface CountryServices {

    List<Country> findAll();

    Country save(Country country);
}
