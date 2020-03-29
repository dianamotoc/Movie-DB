package com.example.demo.model.Persistance;

import com.example.demo.model.DomainObject.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Comment findById(int id);
    void deleteById(int id);
    List<Comment> findAll();
}
