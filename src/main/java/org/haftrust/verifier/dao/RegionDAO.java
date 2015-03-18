package org.haftrust.verifier.dao;

import java.util.List;

import org.haftrust.verifier.model.Country;
import org.haftrust.verifier.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface RegionDAO extends JpaRepository<Region, Integer> {

    List<Region> findByCountry(Country country);

}
