package org.parentsstepahead.psa.controllers;

import org.parentsstepahead.psa.models.Country;
import org.parentsstepahead.psa.services.CountryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    @Autowired
    private CountryServices servicesCountries;

    @GetMapping
    public ResponseEntity<?> getCountries() {
        List<Country> countries = servicesCountries.findAll();
        if(countries.isEmpty()){
            return new ResponseEntity<List<Country>>(countries, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Country>>(countries, HttpStatus.OK);
    }
}
