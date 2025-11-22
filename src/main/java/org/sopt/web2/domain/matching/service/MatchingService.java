package org.sopt.web2.domain.matching.service;

import lombok.RequiredArgsConstructor;
import org.sopt.web2.domain.matching.controller.dto.MatchingResponse;
import org.sopt.web2.domain.matching.entity.Matching;
import org.sopt.web2.domain.matching.entity.Wish;
import org.sopt.web2.domain.matching.exception.MatchingErrorCode;
import org.sopt.web2.domain.matching.repository.MatchingRepository;
import org.sopt.web2.domain.matching.repository.WishRepository;
import org.sopt.web2.domain.user.entity.User;
import org.sopt.web2.global.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchingService {

    private final MatchingRepository matchingRepository;
    private final WishRepository wishRepository;

    @Transactional
    public MatchingResponse getMatching(Long wishId) {
        Wish myWish = wishRepository.findById(wishId)
                .orElseThrow(() -> new BusinessException(MatchingErrorCode.WISH_NOT_FOUND));

        User me = myWish.getUser();

        List<Wish> partnerWishOpt;

        // 1. 원하는 직업이 있으면 직업 포함해서 먼저 검색
        if (myWish.getJob() != null) {
            partnerWishOpt = wishRepository.findMatchingWishWithJob(
                    myWish.getId(),
                    myWish.getLocation(),
                    myWish.getTimeSlot(),
                    myWish.getJob()
            );

            // 2. 직업 매칭 결과 없으면 직업 제외하고 재검색
            if (partnerWishOpt.isEmpty()) {
                partnerWishOpt = wishRepository.findMatchingWishWithoutJob(
                        myWish.getId(),
                        myWish.getLocation(),
                        myWish.getTimeSlot()
                );
            }
        } else {
            // 원하는 직업 없으면 바로 장소/시간만 검색
            partnerWishOpt = wishRepository.findMatchingWishWithoutJob(
                    myWish.getId(),
                    myWish.getLocation(),
                    myWish.getTimeSlot()
            );
        }

        if (partnerWishOpt.isEmpty()) {
            return null;
        }

        Wish partnerWish = partnerWishOpt.get(0);
        User partner = partnerWish.getUser();

        // 매칭 테이블에 저장
        Matching matching = new Matching(me, partner);
        matchingRepository.save(matching);
        LocalDateTime startAt = parseStartTime(myWish.getTimeSlot());


        return MatchingResponse.from(
                partner.getNativeLanguage(),
                startAt,
                myWish.getLocation()
        );
    }

    private LocalDateTime parseStartTime(String timeSlot) {
        String startTime = timeSlot.split("~")[0].trim();
        LocalTime time = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm"));
        return LocalDate.now().atTime(time);
    }
}
