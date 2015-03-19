package org.haftrust.verifier.dao;

import org.haftrust.verifier.model.IdentityDocument;
import org.haftrust.verifier.model.Verifier;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface IdentityDocumentDAO extends JpaRepository<IdentityDocument, Integer> {

    IdentityDocument findOneByVerifier(Verifier verifier);

}
