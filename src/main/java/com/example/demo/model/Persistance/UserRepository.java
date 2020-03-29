package com.example.demo.model.Persistance;

import com.example.demo.model.DomainObject.NullPattern.AbstractUser;
import com.example.demo.model.DomainObject.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(String name);
    User findByEmail(String email);
    User findById(int id);
    AbstractUser findByEmailAndPassword(String email, String password);
    void deleteById(int id);
    AbstractUser findByIsLogged(int isLoggedIn);
}
