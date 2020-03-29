package com.example.demo.model.Persistance;

import com.example.demo.model.DomainObject.Answer;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Integer> {
    Answer findById(int id);
    void deleteById(int id);
}
