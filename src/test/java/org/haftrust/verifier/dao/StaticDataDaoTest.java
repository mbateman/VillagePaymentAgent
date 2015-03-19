package org.haftrust.verifier.dao;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.haftrust.verifier.config.DbConfig;
import org.haftrust.verifier.model.StaticData;
import org.haftrust.verifier.model.Verifier;
import org.haftrust.verifier.model.enums.EmploymentStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
    @ContextConfiguration(classes = { DbConfig.class })
})
@ActiveProfiles({ "scratch" })
public class StaticDataDaoTest {

    private static final LocalDate today = LocalDate.now(ZoneId.of("UTC"));
    
    @Autowired
    private StaticDataDAO staticDataDao;

    @Test
    public void shouldFindEmploymentStatus() {
        StaticData sd = staticDataDao.getEmploymentStatus("Preregistered");
        assertNotNull(sd);
    }
     
    @Test
    public void shouldFindEmployeeType() {
        StaticData sd = staticDataDao.getEmployeeType("Verifier");
        assertNotNull(sd);
    }

    @Test
    public void shouldFindGenders() {
        List<StaticData> sd = staticDataDao.getGenders();
        assertFalse(sd.isEmpty());
    }
    
    @Test
    public void shouldFindEducationLevels() {
        List<StaticData> sd = staticDataDao.getEducationLevels();
        assertFalse(sd.isEmpty());
    }

    @Test
    public void shouldFindEducationTypes() {
        List<StaticData> sd = staticDataDao.getEducationTypes();
        assertFalse(sd.isEmpty());
    }

    @Test
    public void shouldFindIdentityDocumentTypes() {
        List<StaticData> sd = staticDataDao.getIdentityDocumentTypes();
        assertFalse(sd.isEmpty());
    }

    @Test
    public void shouldFindTitles() { 
        List<StaticData> sd = staticDataDao.getTitles();
        assertFalse(sd.isEmpty());
    }
    
    @Test
    public void shouldFindVerificationStatus() {
        StaticData sd = staticDataDao.getVerificationStatus("Verified");
        assertNotNull(sd);
    }
    
    @Test
    public void shouldFindVerificationStatusTypes() {
        List<StaticData> sd = staticDataDao.getVerificationStatusTypes();
        assertFalse(sd.isEmpty());
    }

    @Test
    public void shouldFindInterviewStatus() {
        StaticData sd = staticDataDao.getInterviewStatus("Awaiting Arrangement");
        assertNotNull(sd);
    }

    @Test
    public void shouldFindDeviceAllocation() {
        StaticData sd = staticDataDao.getDeviceAllocation("Yes");
        assertNotNull(sd);
    }

}
