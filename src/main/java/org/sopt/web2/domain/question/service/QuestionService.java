package org.sopt.web2.domain.question.service;

import lombok.RequiredArgsConstructor;
import org.sopt.web2.domain.question.controller.dto.QuestionResponse;
import org.sopt.web2.domain.question.entity.Question;
import org.sopt.web2.domain.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionResponse getRandomQuestions() {
        List<Question> questions = questionRepository.findRandomQuestions();
        return QuestionResponse.from(questions);
    }
}