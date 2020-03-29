package com.example.demo.model.Service;

import com.example.demo.model.DomainObject.Question;

import java.util.List;

public interface QuestionService extends AbstractService {
    void saveQuestion(Question newQuestion);
    void deleteQuestionById(int id);
    void updateQuestion(int id, Question newQuestion);

    Question findQuestionById(int id);

    List<Question> findAllQuestions();
}
