package org.parentsstepahead.psa.services.imp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.parentsstepahead.psa.models.Country;
import org.parentsstepahead.psa.reporsitories.CountryRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryImpTest {

    @Mock
    CountryRepository countryRepository;

    @InjectMocks
    private CountryImp countryImp = new CountryImp();

    private List<Country> countryList;

    @Before
    public void setUp(){
        countryList = new ArrayList<>();
        Country country1 = new Country(1L, "CL", "Colombia");
        Country country2 = new Country(2L, "USA", "United States");
        Country country3 = new Country(3L, "PR", "Puerto Rico");
        countryList.add(country1);
        countryList.add(country2);
        countryList.add(country3);
    }

    @Test
    public void testServiceNotNull() {
        Assert.assertNotNull(countryImp);
    }

    @Test
    public void testFindAll() {
        when(countryRepository.findAll()).thenReturn(countryList);
        List<Country> allCountries = countryImp.findAll();
        Assert.assertEquals(allCountries.size(),3);
    }

    @Test
    public void testSave() {
        Country country = new Country(4L, "CA", "Canada");
        when(countryRepository.save(country)).thenReturn(country);
        Country countryService = countryImp.save(country);
        Assert.assertEquals(countryService.getCountryAbb(),"CA");
    }

    @Test
    public void testDelete() {
        Country country = new Country(4L, "CA", "Canada");

        countryImp.delete(country.getIdCountry());
        verify(countryRepository, times(1)).deleteById(country.getIdCountry());
    }


    @Test
    public void testExist() {
        Country country = new Country(4L, "CA", "Canada");
        when(countryRepository.existsById(country.getIdCountry())).thenReturn(true);
        Assert.assertTrue(countryImp.exist(country.getIdCountry()));
    }

    @Test
    public void updateCountry() {
        Country country = new Country(4L, "CA", "Canada");
        Country newCountry = new Country(4L, "CH", "China");

        when(countryRepository.findByIdCountry(4L)).thenReturn(Optional.of(country));
        countryImp.updateCountry(4L, newCountry);
        country.setCountryName(newCountry.getCountryName());
        country.setCountryAbb(newCountry.getCountryAbb());

        Assert.assertEquals(country.getCountryAbb(), "CH");
        Assert.assertEquals(country.getCountryName(), "China");
    }

    @Test
    public void updateCountryPartiallyWhenCountryNameIsNull() {
        Country country = new Country(4L, "CA", "Canada");
        Country newCountry = new Country(4L, "CH", null);

        when(countryRepository.findByIdCountry(4L)).thenReturn(Optional.of(country));

        countryImp.updateCountryPartially(4L, newCountry);
        country.setCountryAbb(newCountry.getCountryAbb());
        Assert.assertEquals(country.getCountryAbb(), "CH");
        Assert.assertEquals(country.getCountryName(), "Canada");
    }

    @Test
    public void updateCountryPartiallyWhenCountryAbbIsNull() {
        Country country = new Country(4L, "CA", "Canada");
        Country newCountry = new Country(4L, null, "China");

        when(countryRepository.findByIdCountry(4L)).thenReturn(Optional.of(country));

        countryImp.updateCountryPartially(4L, newCountry);
        country.setCountryName(newCountry.getCountryName());
        Assert.assertEquals(country.getCountryAbb(), "CA");
        Assert.assertEquals(country.getCountryName(), "China");
    }

    @Test
    public void updateCountryPartiall() {
        Country country = new Country(4L, "CA", "Canada");
        Country newCountry = new Country(4L, "CH", "China");

        when(countryRepository.findByIdCountry(4L)).thenReturn(Optional.of(country));

        countryImp.updateCountryPartially(4L, newCountry);
        country.setCountryName(newCountry.getCountryName());
        country.setCountryAbb(newCountry.getCountryAbb());
        Assert.assertEquals(country.getCountryAbb(), "CH");
        Assert.assertEquals(country.getCountryName(), "China");
    }

}