package org.sopt.web2.domain.matching.repository;

import org.sopt.web2.domain.matching.entity.Matching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchingRepository extends JpaRepository<Matching, Long> {
}
