package org.parentsstepahead.psa.reporsitories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    //I don't need to set up it because
    //I hava data already in the database
    @Before
    public void setUp(){
    }

    @Test
    public void countryRepositoryNotNUll() {
        Assert.assertNotNull(countryRepository);
    }

    @Test
    public void findAllTest() {
        Assert.assertEquals(countryRepository.findAll().size(),3);
    }
}