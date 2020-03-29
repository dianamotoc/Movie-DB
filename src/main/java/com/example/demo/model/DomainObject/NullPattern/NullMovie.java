package com.example.demo.model.DomainObject.NullPattern;

import com.example.demo.model.DomainObject.MovieActor;

import java.util.List;

public class NullMovie extends AbstractMovie {
    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public int getId() {
        return -1;
    }

    @Override
    public String getRelease_data() {
        return "Not Available in Data Base";
    }

    @Override
    public String getName() {
        return "Not Available in Data Base";
    }

    @Override
    public String getDescription() {
        return "Not Available in Data Base";
    }

    @Override
    public String getPathToImage() {
        return null;
    }

    @Override
    public List<MovieActor> getActorList() {
        return null;
    }
}
