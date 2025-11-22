package org.sopt.web2.domain.matching.repository;

import org.sopt.web2.domain.matching.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
}
