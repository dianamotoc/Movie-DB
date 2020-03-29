package com.example.demo.model.DomainObject;

import com.example.demo.model.DomainObject.NullPattern.AbstractUser;
import com.example.demo.model.Enum.UserEnum;

import javax.persistence.*;
import java.util.List;

@Entity
public class User extends AbstractUser {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String email;
    private String name;
    private String password;
    @Column(name = "is_logged_in")
    private int isLogged;
    @Enumerated(EnumType.STRING)
    private UserEnum type;
    @OneToMany(mappedBy = "user")
    private List<WatchedList> movieWatchedList;
    @OneToMany(mappedBy = "user")
    private List<WishList> movieWishList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEnum getType() {
        return type;
    }

    public void setType(UserEnum type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsLogged() {
        return isLogged;
    }

    public void setIsLogged(int isLoggedIn) {
        this.isLogged = isLoggedIn;
    }

    public List<WatchedList> getMovieWatchedList() {
        return movieWatchedList;
    }

    public void setMovieWatchedList(List<WatchedList> movieWatchedList) {
        this.movieWatchedList = movieWatchedList;
    }

    public List<WishList> getMovieWishList() {
        return movieWishList;
    }

    public void setMovieWishList(List<WishList> movieWishList) {
        this.movieWishList = movieWishList;
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getEmail();
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
