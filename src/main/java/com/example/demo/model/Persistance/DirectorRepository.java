package com.example.demo.model.Persistance;

import com.example.demo.model.DomainObject.Director;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends CrudRepository<Director, Integer> {
    Director findById(int id);
    Director findByName(String name);
    void deleteById(int id);
    List<Director> findAll();
}
