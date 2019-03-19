package org.parentsstepahead.psa.services;

import org.parentsstepahead.psa.models.Country;

import java.util.List;

public interface CountryServices {

    List<Country> findAll();

    Country save(Country country);

    void delete(Long id);

    boolean exist(Long id);

    Country updateCountry(Long id, Country newCountry);

    Country updateCountryPartially(Long id, Country newCountry);
}
