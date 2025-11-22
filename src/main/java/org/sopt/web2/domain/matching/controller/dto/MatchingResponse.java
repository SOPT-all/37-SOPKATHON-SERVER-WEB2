package org.sopt.web2.domain.matching.controller.dto;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import lombok.Builder;
import org.sopt.web2.domain.spot.entity.Spot;
import org.sopt.web2.domain.user.entity.Language;

import java.time.LocalDateTime;

@Builder
public record MatchingResponse(
        Language nativeLanguage,
        LocalDateTime startAt,
        String location
) {
    public static MatchingResponse from(Language nativeLanguage, LocalDateTime startAt, String location) {
        return MatchingResponse.builder()
                .nativeLanguage(nativeLanguage)
                .startAt(startAt)
                .location(location)
                .build();
    }
}
