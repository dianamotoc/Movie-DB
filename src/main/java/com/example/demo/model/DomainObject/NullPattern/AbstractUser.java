package com.example.demo.model.DomainObject.NullPattern;

import com.example.demo.model.DomainObject.User;
import com.example.demo.model.DomainObject.WatchedList;
import com.example.demo.model.DomainObject.WishList;
import com.example.demo.model.Enum.UserEnum;

import java.util.List;

public abstract class AbstractUser {
    public abstract boolean isNull();


    public abstract int getId();
    public abstract String getEmail();
    public abstract String getName();
    public abstract UserEnum getType();
    public abstract int getIsLogged();
    public abstract List<WatchedList> getMovieWatchedList();
    public abstract List<WishList> getMovieWishList();

    public abstract void setId(int id);
    public abstract void setEmail(String email);
    public abstract void setName(String name);
    public abstract void setType(UserEnum userEnum);
    public abstract void setIsLogged(int isLoggedIn);
    public abstract void setMovieWatchedList(List<WatchedList> watchedList);
    public abstract void setMovieWishList(List<WishList> wishList);

}
