package com.example.demo.model.Service;

import com.example.demo.model.DomainObject.Actor;

import java.util.List;

public interface ActorService extends AbstractService {
    final String IMAGE_PATH = "./photos/actor";

    void saveActor(Actor newActor);
    Actor findActorByName(String name);
    void deleteActorById(int id);
    void updateActor(int id, Actor newActor);
    Actor findActorById(int id);
    List<Actor> findAllActors();
}
