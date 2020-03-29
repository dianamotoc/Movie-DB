package com.example.demo.model.Service;

import com.example.demo.model.DomainObject.Answer;

public interface AnswerService extends AbstractService {
    void saveAnswer(Answer newActor);
    void deleteAnswerById(int id);
    void updateAnswer(int id, Answer newAnswer);
}
