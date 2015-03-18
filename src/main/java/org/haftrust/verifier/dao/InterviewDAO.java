package org.haftrust.verifier.dao;

import org.haftrust.verifier.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface InterviewDAO extends JpaRepository<Interview, Integer> {
}
