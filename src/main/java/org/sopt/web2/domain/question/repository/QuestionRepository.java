package org.sopt.web2.domain.question.repository;

import org.sopt.web2.domain.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT * FROM question ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<Question> findRandomQuestions();
}