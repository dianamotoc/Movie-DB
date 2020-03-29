package com.example.demo.model.Service;

import com.example.demo.model.DomainObject.Movie;
import com.example.demo.model.DomainObject.NullPattern.AbstractMovie;

import java.util.List;

public interface MovieService extends AbstractService {
    void saveMovie(Movie newMovie);
    AbstractMovie findMovieByName(String name);
    void deleteMovieById(int id);
    void updateMovie(int id, Movie newMovie);
    List<Movie> findAllMovies();

    AbstractMovie findMovieById(int id);
}
