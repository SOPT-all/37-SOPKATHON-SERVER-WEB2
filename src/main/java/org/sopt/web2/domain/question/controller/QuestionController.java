package org.sopt.web2.domain.question.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.web2.domain.question.controller.dto.QuestionResponse;
import org.sopt.web2.domain.question.service.QuestionService;
import org.sopt.web2.global.response.CommonApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<CommonApiResponse<QuestionResponse>> getRandomQuestions() {
        QuestionResponse response = questionService.getRandomQuestions();
        return ResponseEntity.ok(
                CommonApiResponse.success(HttpStatus.OK, "질문이 조회되었습니다.", response)
        );
    }
}
