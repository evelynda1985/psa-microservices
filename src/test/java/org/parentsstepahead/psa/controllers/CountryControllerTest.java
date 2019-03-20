package org.parentsstepahead.psa.controllers;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.parentsstepahead.psa.PsaApplication;
import org.parentsstepahead.psa.models.Country;
import org.parentsstepahead.psa.reporsitories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PsaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CountryControllerTest {

    private  final String BASE_URL = "/api/v1/countries";

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    CountryRepository repository;

    Country country1;
    Country country2;
    Country country3;

    @Before
    public void setUp() {
        country1 = new Country(1L, "CL", "Colombia");
        country2 = new Country(2L, "USA", "United States");
        country3 = new Country(3L, "PR", "Puerto Rico");
        country1 = repository.save(country1);
        country2 = repository.save(country2);
        country3 = repository.save(country3);
    }

    @After
    public void cleanData() {
        repository.deleteAll();
    }

    @Test
    public void testGetCountries() {
        ResponseEntity<Country[]> result = testRestTemplate.exchange(BASE_URL, HttpMethod.GET, new HttpEntity<>(null), Country[].class);
        assertTrue(result.getBody().length == 3);
        Assert.assertEquals(result.getStatusCode().value(), 200);
    }


    @Test
    public void testGetCountriesNoCountriesFound() {
        repository.deleteAll();
        ResponseEntity<Country[]> result = testRestTemplate.exchange(BASE_URL, HttpMethod.GET, new HttpEntity<>(null), Country[].class);
        Assert.assertEquals(result.getStatusCode().value(), 404);
    }


    @Test
    public void testAddCountry(){
        HttpEntity<Country> entity = new HttpEntity<>(new Country(1L, "CL", "Colombia"));
        ResponseEntity<Country> result = testRestTemplate.postForEntity(BASE_URL, entity, Country.class);
        Country country = result.getBody();
        Assert.assertTrue(country.getIdCountry() > 0);
        Assert.assertEquals(result.getStatusCode().value(), 201);
    }

    @Test
    public void testAddCountryBadRequest(){
        Country newCountry = new Country();
        HttpEntity<Country> entity = new HttpEntity<>(newCountry);
        ResponseEntity<Country> result = testRestTemplate.exchange(BASE_URL, HttpMethod.POST, entity, Country.class);
        Country country = result.getBody();
        Assert.assertEquals(result.getStatusCode().value(), 400);
    }


    @Test
    public void testUpdateCountry(){
        String request="/api/v1/countries/"+ country1.getIdCountry() + "/";
        HttpEntity<Country> entity = new HttpEntity<>(new Country(country1.getIdCountry(), "PR", "Puerto Rico"));
        ResponseEntity<Country> updateCountry = testRestTemplate.exchange(request,HttpMethod.PUT, entity, Country.class);
        Assert.assertEquals(updateCountry.getStatusCode().value(), 200);
    }

    @Test
    public void testUpdateCountryNoExistingId(){
        String request="/api/v1/countries/99999999999999/";
        HttpEntity<Country> entity = new HttpEntity<>(new Country(99999999999999L, "PR", "Puerto Rico"));
        ResponseEntity<Country> updateCountry = testRestTemplate.exchange(request,HttpMethod.PUT, entity, Country.class);
        Assert.assertEquals(updateCountry.getStatusCode().value(), 404);
    }



//    @Test
//    public void testUpdateCountryPartiallyOrComplete(){
//        String request="/api/v1/countries/2/";
//        HttpEntity<Country> entity = new HttpEntity<>(new Country(2L, "AB", "Puerto Rico"));
//        ResponseEntity<Country> updateCountry = testRestTemplate.exchange(request,HttpMethod.PATCH, entity, Country.class);
//        updateCountry.getBody().setCountryAbb("AB");
//        Assert.assertEquals(updateCountry.getStatusCode().value(), 200);
//        Assert.assertEquals(updateCountry.getBody().getCountryAbb(),"AB");
//    }


    @Test
    public void testDeleteCountry(){
        String request="/api/v1/countries/"+ country1.getIdCountry() + "/";
        ResponseEntity<Country> deleteCountry = testRestTemplate.exchange(request,HttpMethod.DELETE, new HttpEntity<>(null),Country.class);
        Assert.assertEquals(deleteCountry.getStatusCode().value(), 200);
    }

    @Test
    public void testDeleteCountryIdNoExist(){
        String request="/api/v1/countries/"+ 9999 + "/";
        Country newCountry = new Country();
        HttpEntity<Country> entity = new HttpEntity<>(newCountry);
        ResponseEntity<Country> result = testRestTemplate.exchange(request, HttpMethod.DELETE, entity, Country.class);
        Assert.assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
    }


}