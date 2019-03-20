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

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }

    @Override
    public boolean exist(Long id) {
        return (id == null)?false: countryRepository.existsByIdCountry(id);
    }

    @Override
    public Country updateCountry(Long id, Country newCountry) {
        Country country = countryRepository.findByIdCountry(id).get();
        country.setCountryName(newCountry.getCountryName());
        country.setCountryAbb(newCountry.getCountryAbb());
        return countryRepository.save(country);
    }

    @Override
    public Country updateCountryPartially(Long id, Country newCountry) {
        Country country = countryRepository.findByIdCountry(id).get();
        if (!(newCountry.getCountryAbb() == null)) {
            country.setCountryAbb(newCountry.getCountryAbb());
        }
        if (!(newCountry.getCountryName() == null)) {
            country.setCountryName(newCountry.getCountryName());
        }
        return countryRepository.save(country);
    }

}
