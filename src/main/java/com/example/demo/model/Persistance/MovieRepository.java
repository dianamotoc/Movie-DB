package com.example.demo.model.Persistance;

import com.example.demo.model.DomainObject.Movie;
import com.example.demo.model.DomainObject.NullPattern.AbstractMovie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
    AbstractMovie findById(int id);
    AbstractMovie findByName(String name);
    void deleteById(int id);
    List<Movie> findAll();
}
