package com.example.demo.model.Service;

import com.example.demo.model.DomainObject.NullPattern.AbstractUser;
import com.example.demo.model.DomainObject.User;

import java.sql.SQLIntegrityConstraintViolationException;

public interface UserService extends AbstractService {
    void saveUser(AbstractUser newUSer) throws SQLIntegrityConstraintViolationException;
    AbstractUser findUserByName(String name);
    AbstractUser findUserByEmail(String email);
    AbstractUser findUserById(int id);
    AbstractUser findUserByEmailAndPassword(String email, String password);
    void deleteUserById(int id);
    void updateUser(int id, User newUser);
    AbstractUser whichUserIsLoggedIn();
}
