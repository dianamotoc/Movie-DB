package com.example.demo.model.Service;

import com.example.demo.model.DomainObject.Director;
import com.example.demo.model.Exception.UnFoundResultData;
import com.example.demo.model.Persistance.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    DirectorRepository directorRepository;

    @Override
    public void saveDirector(Director newDirector) {
        directorRepository.save(newDirector);
    }

    @Override
    public Director findDirectorByName(String name) {
        return directorRepository.findByName(name);
    }

    @Override
    public void deleteDirectorById(int id) throws UnFoundResultData{
        if(null == directorRepository.findById(id)){
            throw new UnFoundResultData();
        }
        directorRepository.deleteById(id);
    }

    @Override
    public void updateDirector(int id, Director newDirector) {
        Director oldDirector = directorRepository.findById(id);
        if(null == oldDirector){
            throw new UnFoundResultData();
        }
        oldDirector.setName(newDirector.getName());
        oldDirector.setAge(newDirector.getAge());
        oldDirector.setDescription(newDirector.getDescription());
        oldDirector.setPathToImage(newDirector.getPathToImage());

        directorRepository.save(oldDirector);
    }

    @Override
    public Director findDirectorById(int id) {
        return directorRepository.findById(id);
    }

    @Override
    public List<Director> findAllDirectors()
        {
            return directorRepository.findAll();
    }
}
