package com.example.demo.model.DomainObject;
import javax.persistence.*;
import java.util.List;

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private int age;
    private String description;
    @Column(name = "path_to_image")
    private String pathToImage;
    @OneToMany(mappedBy = "actor")
    List<MovieActor> movieList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String path_to_image) {
        this.pathToImage = path_to_image;
    }

    public String toString(){
        return this.name + " " + this.age + " " + this.pathToImage + " " + this.description;
    }

    public List<MovieActor> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieActor> movieList) {
        this.movieList = movieList;
    }
}
