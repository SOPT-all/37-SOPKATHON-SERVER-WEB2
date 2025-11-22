package org.sopt.web2.domain.spot.repository;

import org.sopt.web2.domain.spot.entity.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Long> {

    @Query("SELECT s FROM Spot s WHERE s.spotName LIKE :searchWord%")
    List<Spot> findBySpotNameStartingWith(@Param("searchWord") String searchWord);
    
}
