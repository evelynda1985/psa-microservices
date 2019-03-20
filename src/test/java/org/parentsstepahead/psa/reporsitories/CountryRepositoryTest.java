package org.parentsstepahead.psa.reporsitories;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.parentsstepahead.psa.PsaApplication;
import org.parentsstepahead.psa.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = PsaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    Country country1;
    Country country2;
    Country country3;
    Country country4;

    //I don't need to set up it because when I have data in the database in the
    //folder resources spring look for import.sql if is there it imports automatically
    @Before
    public void setUp() {
        country1 = new Country(1L, "CL", "Colombia");
        country2 = new Country(2L, "USA", "United States");
        country3 = new Country(3L, "PR", "Puerto Rico");
        country4 = new Country(3L, "CA", "Canada");
        country1 = countryRepository.save(country1);
        country2 = countryRepository.save(country2);
        country3 = countryRepository.save(country3);
        country4 = countryRepository.save(country4);
    }

    @After
    public void cleanData() {
        countryRepository.deleteAll();
    }

    @Test
    public void testCountryRepositoryNotNUll() {
        Assert.assertNotNull(countryRepository);
    }

    @Test
    public void testFindAllTest() {
        Assert.assertEquals(countryRepository.findAll().size(),4);
    }

}