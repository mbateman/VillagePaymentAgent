package org.haftrust.verifier.dao;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.ZoneId;

import org.haftrust.verifier.config.DbConfig;
import org.haftrust.verifier.model.Verifier;
import org.haftrust.verifier.model.enums.EmploymentStatus;
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
public class VerifierDaoTest {

    private static final LocalDate today = LocalDate.now(ZoneId.of("UTC"));
    
    @Autowired
    private VerifierDAO verifierDao;

    @Test
    public void shouldSaveAndFindEntities() {
        Verifier verifier = new Verifier();
        verifier.setFirstName("Susan");
        verifier.setLastName("Calvin");
        verifier.setEmail("susan@usrmm.com");
        verifier.setPassword("password");
        verifier.setStatus(EmploymentStatus.PRE_REGISTERED);
        verifier.setStatusDate(java.sql.Date.valueOf(today));
        
        Verifier savedEntity = verifierDao.saveAndFlush(verifier);
        
        assertNotNull("Should have been assigned an ID, when saved.", savedEntity.getId());
        
        Verifier foundEntity = verifierDao.findOne(savedEntity.getId());
        assertNotNull("Should have found verifier by ID.", savedEntity);
        assertEquals("ID of found entity should be same as that saved.", 
                savedEntity.getId(), foundEntity.getId());
        
    }

}
