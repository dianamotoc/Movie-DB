package com.example.demo.model.Persistance;

import com.example.demo.model.DomainObject.Actor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Integer> {
    Actor findById(int id);
    Actor findByName(String name);
    void deleteById(int id);
    List<Actor> findAll();
}
