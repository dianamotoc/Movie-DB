package com.example.demo.model.DomainObject.NullPattern;

import com.example.demo.model.DomainObject.MovieActor;

import java.util.List;

public abstract class AbstractMovie {
    public abstract boolean isNull();

    public abstract int getId();
    public abstract String getRelease_data();
    public abstract String getName();
    public abstract String getDescription();
    public abstract String getPathToImage();
    public abstract List<MovieActor> getActorList();
}
