package com.example.demo.model.Service;

import com.example.demo.model.DomainObject.Award;
import com.example.demo.model.Exception.UnFoundResultData;
import com.example.demo.model.Persistance.AwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwardServiceImpl implements AwardService {

    @Autowired
    AwardRepository awardRepository;

    @Override
    public void saveAward(Award newAward) {
        awardRepository.save(newAward);
    }

    @Override
    public Award findAwardByName(String name) {
        return awardRepository.findByName(name);
    }

    @Override
    public void deleteAwardById(int id) throws UnFoundResultData{
        if(null == awardRepository.findById(id)){
            throw new UnFoundResultData();
        }
        awardRepository.deleteById(id);
    }

    @Override
    public void updateAward(int id, Award newAward) {
        Award oldAward = awardRepository.findById(id);
        if(null == oldAward){
            throw new UnFoundResultData();
        }
        oldAward.setName(newAward.getName());

        awardRepository.save(oldAward);
    }

    @Override
    public List<Award> findAllAwards() {
        return awardRepository.findAll();
    }

    @Override
    public Award findAwardById(int id) {
        return awardRepository.findById(id);
    }
}