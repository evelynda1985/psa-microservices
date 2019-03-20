package org.parentsstepahead.psa.controllers;

import org.parentsstepahead.psa.Errors.ValidationErrorBuilder;
import org.parentsstepahead.psa.models.Country;
import org.parentsstepahead.psa.services.CountryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping()
    public ResponseEntity<?> addCountry(@Valid @RequestBody Country country, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }
        return new ResponseEntity<Country>(servicesCountries.save(country), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "{id}/")
    public ResponseEntity<?> deleteCountry(@PathVariable Long id){
        if(servicesCountries.exist(id)) {
            servicesCountries.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "{id}/")
    public ResponseEntity<?> updateCountry(@PathVariable Long id, @RequestBody Country newCountry){
        if (servicesCountries.exist(id))
        {
            return new ResponseEntity<Country>(servicesCountries.updateCountry(id,newCountry),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping(value = "{id}/")
    public ResponseEntity<?> updateCountryPartiallyOrComplete(@PathVariable Long id, @RequestBody Country newCountry){
        if (servicesCountries.exist(id))
        {
            return new ResponseEntity<Country>(servicesCountries.updateCountryPartially(id, newCountry),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
