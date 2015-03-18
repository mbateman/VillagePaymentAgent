package org.haftrust.verifier.dao;

import java.util.List;

import org.haftrust.verifier.model.Bank;
import org.haftrust.verifier.model.Verifier;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface BankDAO extends JpaRepository<Bank, Integer> {

    List<Bank> findByAccountNumber(String account);
    
    Bank findOneByVerifier(Verifier verifier);
    
    Bank findOneByVerifierAndEmployeeType(Verifier verifier, String employeeType);

}
