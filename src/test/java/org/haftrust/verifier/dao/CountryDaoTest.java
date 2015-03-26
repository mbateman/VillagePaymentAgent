package org.haftrust.verifier.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.haftrust.verifier.config.DbConfig;
import org.haftrust.verifier.model.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
    @ContextConfiguration(classes = { DbConfig.class })
})
@ActiveProfiles({ "scratch" })
public class CountryDaoTest {

    @Autowired
    private CountryDAO countryDao;

    @Test
    public void shouldFindCountries() {
        List<Country> countries = countryDao.findAll();
        assertFalse("Should have found some countries.", countries.isEmpty());
    }
    
    @Test
    public void shouldFindCountryById() {
        Country country = countryDao.findOne(1);
        assertNotNull(country);
    }

}
