package org.haftrust.verifier.dao;

import org.haftrust.verifier.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface ImageDAO extends JpaRepository<Image, Integer> {
}
