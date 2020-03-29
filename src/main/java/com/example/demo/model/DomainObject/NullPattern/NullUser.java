package com.example.demo.model.DomainObject.NullPattern;

import com.example.demo.model.DomainObject.User;
import com.example.demo.model.DomainObject.WatchedList;
import com.example.demo.model.DomainObject.WishList;
import com.example.demo.model.Enum.UserEnum;

import java.util.List;

//Completeaza pattern ul
public class NullUser extends AbstractUser {

    public NullUser() {
    }

    @Override
    public int getId() {
        return -1;
    }

    @Override
    public String getEmail() {
        return "Not Available in Data Base";
    }

    @Override
    public String getName() {
        return "Not Available in Data Base";
    }

    @Override
    public UserEnum getType() {
        return UserEnum.simple;
    }

    @Override
    public int getIsLogged() {
        return 0;
    }

    @Override
    public List<WatchedList> getMovieWatchedList() {
        return null;
    }

    @Override
    public List<WishList> getMovieWishList() {
        return null;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setType(UserEnum userEnum) {

    }

    @Override
    public void setIsLogged(int isLoggedIn) {

    }

    @Override
    public void setMovieWatchedList(List<WatchedList> watchedList) {

    }

    @Override
    public void setMovieWishList(List<WishList> wishList) {

    }

    public boolean isNull(){
        return true;
    }
}
