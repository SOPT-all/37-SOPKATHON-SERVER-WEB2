package org.sopt.web2.domain.question.controller.dto;

import org.sopt.web2.domain.question.entity.Question;

import java.util.List;

public record QuestionResponse(
        List<String> questions
) {
    public static QuestionResponse from(List<Question> questions) {
        List<String> contents = questions.stream()
                .map(Question::getContent)
                .toList();
        return new QuestionResponse(contents);
    }
}
