package org.haftrust.verifier.dao;

import java.util.List;

import org.haftrust.verifier.model.Verifier;
import org.haftrust.verifier.model.enums.EmployeeType;
import org.haftrust.verifier.model.enums.EmploymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VerifierDAO extends JpaRepository<Verifier, Integer> {

    List<Verifier> findByEmail(String email);
    
    List<Verifier> findByEmailAndStatus(String email, EmploymentStatus employmentStatus);
    
    List<Verifier> findByEmailAndPasswordAndStatus(String email, String password, EmploymentStatus employmentStatus);

    @Query("select v from Verifier v where v.address.employeeType = :employeeType")
    List<Verifier> findByEmployeeType(EmployeeType employeeType);

}
