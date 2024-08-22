package com.springBoot.QuizApplication.Model;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class quizResponse {
    private Integer id;
    private String quizResponse;
}
