package org.sopt.web2.domain.spot.service;

import lombok.RequiredArgsConstructor;
import org.sopt.web2.domain.spot.controller.dto.SpotResponse;
import org.sopt.web2.domain.spot.entity.Spot;
import org.sopt.web2.domain.spot.repository.SpotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SpotService {

    private final SpotRepository spotRepository;

    public List<SpotResponse> getSelectedSpots(String searchWord) {
        List<Spot> spots;

        if (searchWord == null || searchWord.isEmpty()) {
            spots = spotRepository.findAll();
        } else {
            spots = spotRepository.findBySpotNameStartingWith(searchWord);
        }

        return spots.stream()
                .map(SpotResponse::from)
                .toList();
    }
}
