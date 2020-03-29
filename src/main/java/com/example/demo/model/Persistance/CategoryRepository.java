package com.example.demo.model.Persistance;

import com.example.demo.model.DomainObject.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Category findById(int id);
    void deleteById(int id);
}