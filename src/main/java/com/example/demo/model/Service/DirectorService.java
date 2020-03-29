package com.example.demo.model.Service;

import com.example.demo.model.DomainObject.Director;

import java.util.List;

public interface DirectorService extends AbstractService {
    void saveDirector(Director newDirector);
    Director findDirectorByName(String name);
    void deleteDirectorById(int id);
    void updateDirector(int id, Director newDirector);

    Director findDirectorById(int id);
    List<Director> findAllDirectors();
}
