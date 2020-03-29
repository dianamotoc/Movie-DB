package com.example.demo.model.DomainObject;

import com.example.demo.model.DomainObject.NullPattern.AbstractMovie;

import javax.persistence.*;
import java.util.List;

@Entity
public class Movie extends AbstractMovie {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private String release_data;
    private String description;
    @Column(name = "path_to_image")
    private String pathToImage;
    @OneToMany(mappedBy = "movie")
    List<MovieActor> actorList;
    @OneToMany(mappedBy = "movie")
    List<MovieCategory> categoryList;
    @OneToMany(mappedBy = "movie")
    List<WatchedList> userWatchedList;
    @OneToMany(mappedBy = "movie")
    List<WishList> userWishList;
    @ManyToOne
    @JoinColumn(name="id_director")
    private Director director;
    @Column(name = "trailer_link")
    private String youtubeTrailerLink;
    @OneToMany
    private List<Comment> commentList;

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

    public String getRelease_data() {
        return release_data;
    }

    public void setRelease_data(String release_data) {
        this.release_data = release_data;
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

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public List<MovieActor> getActorList() {
        return actorList;
    }

    public void setActorList(List<MovieActor> actorList) {
        this.actorList = actorList;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public String getYoutubeTrailerLink() {
        return youtubeTrailerLink;
    }

    public void setYoutubeTrailerLink(String youtubeTrailerLink) {
        this.youtubeTrailerLink = youtubeTrailerLink;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", release_data='" + release_data + '\'' +
                ", description='" + description + '\'' +
                ", pathToImage='" + pathToImage + '\'' +
                ", actorList=" + actorList +
                '}';
    }
}
