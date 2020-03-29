package com.example.demo.model.Persistance;

import com.example.demo.model.DomainObject.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
    Question findById(int id);
    void deleteById(int id);
    List<Question> findAll();
}
