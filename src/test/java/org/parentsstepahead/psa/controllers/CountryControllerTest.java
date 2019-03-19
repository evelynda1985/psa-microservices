package org.parentsstepahead.psa.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.parentsstepahead.psa.PsaApplication;
import org.parentsstepahead.psa.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PsaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CountryControllerTest {

    private  final String BASE_URL = "/api/v1/countries";

    @Autowired
    TestRestTemplate testRestTemplate;

//    @Autowired
//    CountryRepository depurar;
protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;


    @Before
    public void setUp() {
    }

    @Test
    public void testGetCountries() {
        ResponseEntity<Country[]> result = testRestTemplate.exchange(BASE_URL, HttpMethod.GET, new HttpEntity<>(null), Country[].class);
        assertTrue(result.getBody().length > 0);
        Assert.assertEquals(result.getStatusCode().value(), 200);
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
    public void testUpdateCountry(){
//        List<Country> testing = depurar.findAll();
//        System.out.println("--------------------------->"+depurar.findAll().size());
        String request="/api/v1/countries/2/";
        HttpEntity<Country> entity = new HttpEntity<>(new Country(2L, "PR", "Puerto Rico"));
        ResponseEntity<Country> updateCountry = testRestTemplate.exchange(request,HttpMethod.PUT, entity, Country.class);
        Assert.assertEquals(updateCountry.getStatusCode().value(), 200);
        Assert.assertEquals(updateCountry.getBody().getCountryAbb(),"PR");
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
        //List<Country> testing = depurar.findAll();
        String request="/api/v1/countries/1/";
        ResponseEntity<Country> deleteCountry = testRestTemplate.exchange(request,HttpMethod.DELETE, new HttpEntity<>(null),Country.class);
        Assert.assertEquals(deleteCountry.getStatusCode().value(), 200);
    }

}