package com.example.demo.model.Service;

import com.example.demo.model.DomainObject.Award;

import java.util.List;

public interface AwardService extends AbstractService {
    void saveAward(Award newAward);
    Award findAwardByName(String name);
    void deleteAwardById(int id);
    void updateAward(int id, Award newAward);
    List<Award> findAllAwards();
    Award findAwardById(int id);
}
