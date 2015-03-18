package org.haftrust.verifier.dao;

import java.util.List;

import org.haftrust.verifier.model.Reference;
import org.haftrust.verifier.model.Verifier;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface ReferenceDAO extends JpaRepository<Reference, Integer> {

    List<Reference> findByVerifier(Verifier verifier);
    
}
