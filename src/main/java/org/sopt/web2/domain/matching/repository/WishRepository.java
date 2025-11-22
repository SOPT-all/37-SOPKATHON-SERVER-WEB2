package org.sopt.web2.domain.matching.repository;

import java.util.Optional;

import org.sopt.web2.domain.matching.entity.Wish;
import org.sopt.web2.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
    Optional<Wish> findByUser(User user);
    @Query("""
        SELECT w FROM Wish w
        WHERE w.id != :wishId
        AND w.location = :location
        AND w.timeSlot = :timeSlot
        AND (:job IS NULL OR w.user.job = :job)
        """)
    List<Wish> findMatchingWishWithJob(
            @Param("wishId") Long wishId,
            @Param("location") String location,
            @Param("timeSlot") String timeSlot,
            @Param("job") String job
    );

    @Query("""
        SELECT w FROM Wish w
        WHERE w.id != :wishId
        AND w.location = :location
        AND w.timeSlot = :timeSlot
        ORDER BY w.id ASC
        """)
    List<Wish> findMatchingWishWithoutJob(
            @Param("wishId") Long wishId,
            @Param("location") String location,
            @Param("timeSlot") String timeSlot
    );

	Optional<Wish> findTopByUserIdOrderByIdDesc(Long userId);

}