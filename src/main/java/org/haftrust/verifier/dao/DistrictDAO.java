package org.haftrust.verifier.dao;

import java.util.List;

import org.haftrust.verifier.model.District;
import org.haftrust.verifier.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface DistrictDAO extends JpaRepository<District, Integer> {

    List<District> findByRegion(Region r);

}
