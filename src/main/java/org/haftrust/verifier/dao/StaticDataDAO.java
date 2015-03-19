package org.haftrust.verifier.dao;

import java.util.List;

import org.haftrust.verifier.model.StaticData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Miroslav
 */
public interface StaticDataDAO extends JpaRepository<StaticData, Integer> {

    @Query("select sd from StaticData sd where sd.type='employment status' and sd.description= :description")
    StaticData getEmploymentStatus(@Param("description") String description);
    
    @Query("select sd from StaticData sd where sd.type='employee type' and sd.description= :description")
    StaticData getEmployeeType(@Param("description") String description);

    @Query("select sd from StaticData sd where sd.type='gender'")
    List<StaticData> getGenders();

    @Query("select sd from StaticData sd where sd.type='education level'")
    List<StaticData> getEducationLevels();

    @Query("select sd from StaticData sd where sd.type='education type'")
    List<StaticData> getEducationTypes();

    @Query("select sd from StaticData sd where sd.type='identity document type'")
    List<StaticData> getIdentityDocumentTypes();

    @Query("select sd from StaticData sd where sd.type='title'")
    List<StaticData> getTitles();

    @Query("select sd from StaticData sd where sd.type='verification status' and sd.description= :description")
    StaticData getVerificationStatus(@Param("description") String description);

    @Query("select sd from StaticData sd where sd.type='verification status'")
    List<StaticData> getVerificationStatusTypes();

    @Query("select sd from StaticData sd where sd.type='interview status' and sd.description= :description")
    StaticData getInterviewStatus(@Param("description") String description);

    @Query("select sd from StaticData sd where sd.type='device allocation' and sd.description= :description")
    StaticData getDeviceAllocation(@Param("description") String description);

}
