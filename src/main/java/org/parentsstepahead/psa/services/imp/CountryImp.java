package org.parentsstepahead.psa.services.imp;

import org.parentsstepahead.psa.models.Country;
import org.parentsstepahead.psa.reporsitories.CountryRepository;
import org.parentsstepahead.psa.services.CountryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryImp implements CountryServices {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }
}
