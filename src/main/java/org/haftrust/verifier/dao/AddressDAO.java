package org.haftrust.verifier.dao;

import java.util.List;

import org.haftrust.verifier.model.Address;
import org.haftrust.verifier.model.Region;
import org.haftrust.verifier.model.Verifier;
import org.haftrust.verifier.model.enums.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface AddressDAO extends JpaRepository<Address, Integer> {

    Address findByVerifier(Verifier verifier);

    List<Address> findByRegion(Region region);
    
    List<Address> findByRegionAndEmployeeType(Region region, EmployeeType employeeType);

}
