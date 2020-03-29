package com.example.demo.model.Persistance;

import com.example.demo.model.DomainObject.Award;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRepository extends CrudRepository<Award, Integer> {
    Award findById(int id);
    Award findByName(String name);
    void deleteById(int id);
    List<Award> findAll();
}
