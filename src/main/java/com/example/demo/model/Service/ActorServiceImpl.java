package com.example.demo.model.Service;

import com.example.demo.model.DomainObject.Actor;
import com.example.demo.model.Exception.UnFoundResultData;
import com.example.demo.model.Persistance.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorRepository actorRepository;

    @Override
    public void saveActor(Actor newActor) {
        actorRepository.save(newActor);
    }

    @Override
    public Actor findActorByName(String name) {
        return actorRepository.findByName(name);
    }

    @Override
    public void deleteActorById(int id) throws UnFoundResultData{
        if(null == actorRepository.findById(id)){
            throw new UnFoundResultData();
        }
        actorRepository.deleteById(id);
    }

    @Override
    public void updateActor(int id, Actor newActor) {
        Actor oldActor = actorRepository.findById(id);
        if(null == oldActor){
            throw new UnFoundResultData();
        }
        oldActor.setName(newActor.getName());
        oldActor.setAge(newActor.getAge());
        oldActor.setDescription(newActor.getDescription());
        oldActor.setPathToImage(newActor.getPathToImage());

        actorRepository.save(oldActor);
    }

    @Override
    public Actor findActorById(int id) {
            return actorRepository.findById(id);
    }

    @Override
    public List<Actor> findAllActors() {
        return actorRepository.findAll();
    }
}
