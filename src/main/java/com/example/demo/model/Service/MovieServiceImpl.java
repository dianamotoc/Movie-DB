package com.example.demo.model.Service;

import com.example.demo.model.DomainObject.Movie;
import com.example.demo.model.DomainObject.NullPattern.AbstractMovie;
import com.example.demo.model.DomainObject.NullPattern.NullMovie;
import com.example.demo.model.Exception.UnFoundResultData;
import com.example.demo.model.Persistance.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public void saveMovie(Movie newMovie) {
        movieRepository.save(newMovie);
    }

    @Override
    public AbstractMovie findMovieByName(String name) {
        AbstractMovie movie = movieRepository.findByName(name);
        if(null == movie){
            return new NullMovie();
        }
        return movie;
    }

    @Override
    public void deleteMovieById(int id) {
        if(null == movieRepository.findById(id)){
            throw new UnFoundResultData();
        }
        movieRepository.deleteById(id);
    }

    @Override
    public void updateMovie(int id, Movie newMovie) {
        AbstractMovie oldMovie = movieRepository.findById(id);
        if(!oldMovie.isNull()){
            throw new UnFoundResultData();
        }
        oldMovie = (Movie) oldMovie;
        ((Movie) oldMovie).setName(newMovie.getName());
        ((Movie) oldMovie).setRelease_data(newMovie.getRelease_data());
        ((Movie) oldMovie).setDescription(newMovie.getDescription());
        ((Movie) oldMovie).setPathToImage(newMovie.getPathToImage());

        movieRepository.save((Movie) oldMovie);
    }

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public AbstractMovie findMovieById(int id) {
        return movieRepository.findById(id);
    }

}
