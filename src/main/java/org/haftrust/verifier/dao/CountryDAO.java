package org.haftrust.verifier.dao;

import org.haftrust.verifier.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface CountryDAO extends JpaRepository<Country, Integer> {
}
