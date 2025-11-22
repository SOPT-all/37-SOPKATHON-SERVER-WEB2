package org.sopt.web2.domain.spot.controller.dto;

import lombok.Builder;
import org.sopt.web2.domain.spot.entity.Spot;

@Builder
public record SpotResponse(
        Long id,
        String spotName
) {
    public static SpotResponse from(Spot spot) {
        return SpotResponse.builder()
                .id(spot.getId())
                .spotName(spot.getSpotName())
                .build();
    }
}
