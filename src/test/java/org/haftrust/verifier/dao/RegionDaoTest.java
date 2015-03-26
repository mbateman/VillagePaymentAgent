package org.haftrust.verifier.dao;

import static org.junit.Assert.*;

import org.haftrust.verifier.config.DbConfig;
import org.haftrust.verifier.model.Region;
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
public class RegionDaoTest {
    
    @Autowired
    private RegionDAO regionDao;

    @Test
    public void shouldFindRegionById() {
        Region region = regionDao.findOne(1);
        assertNotNull(region);
    }

}
