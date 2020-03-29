package com.example.demo.Controller;

import com.example.demo.model.DomainObject.Comment;
import com.example.demo.model.DomainObject.Movie;
import com.example.demo.model.DomainObject.MovieActor;
import com.example.demo.model.DomainObject.NullPattern.AbstractMovie;
import com.example.demo.model.Exception.UnFoundResultData;
import com.example.demo.model.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    DirectorService directorService;

    @Autowired
    ActorService actorService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @PostMapping("/addMovie")
    public String addActor(@ModelAttribute Movie movie){
        movieService.saveMovie(movie);
        return "index";
    }

    @GetMapping("/movieForm")
    public String movieForm(Model model) {
        model.addAttribute("directors", directorService.findAllDirectors());
        model.addAttribute("movie", new Movie());
        model.addAttribute("actors", actorService.findAllActors());
        return "movieForm";
    }

    @GetMapping("/findMovieByName")
    public String findMovieByName(@RequestParam(name="movieName", required=false, defaultValue="World") String movieName, Model model){
        model.addAttribute("isLoggedIn", userService.whichUserIsLoggedIn() != null);
        /*AbstractMovie movie = movieService.findMovieByName(movieName);
        System.out.println(movie);
        for(MovieActor i: movie.getActorList()){
            System.out.println(i.getActor());
        }
        model.addAttribute("movie", movie);*/
        return "redirect:title?name=" + movieName;
    }

    @GetMapping("/deleteMovie")
    public String deleteMovieById(@RequestParam(name="id", required=false, defaultValue="World") int id){
        try {
            movieService.deleteMovieById(id);
        }
        catch (UnFoundResultData e){
            return e.redirect_page();
        }
        return "movieForm";
    }

    @PostMapping("/updateMovie")
    public String updateMovie(@ModelAttribute Movie movie, @RequestParam(name = "id", required = true) int id){
        movieService.updateMovie(id,movie);
        return "index";
    }

    @GetMapping("/updateMovieForm")
    public String updateMovieForm(Model model, @RequestParam(name = "id", required = true) int id) {
        Movie oldMovie = (Movie) movieService.findMovieById(id);
        model.addAttribute("oldMmovie", oldMovie);
        return "movieUpdateForm";
    }

    @GetMapping("/listMovies")
    public String listMovies(Model model) {
        model.addAttribute("isLoggedIn", userService.whichUserIsLoggedIn() != null);
        model.addAttribute("movies", movieService.findAllMovies());
        return "listMovies";
    }

    @GetMapping("/title")
    public String renderFilmPage(Model model, @RequestParam(name="name", required = true) String name){
        AbstractMovie movie = movieService.findMovieByName(name);
        Movie goodMovie = (Movie) movie;
        model.addAttribute("movie", goodMovie);
        model.addAttribute("comment", new Comment());
        model.addAttribute("isLoggedIn", userService.whichUserIsLoggedIn());
        model.addAttribute("comments", commentService.findAllComments());

        return "filmTemplate";
    }

}
