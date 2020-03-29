package com.example.demo.model.Service;

import com.example.demo.model.DomainObject.NullPattern.AbstractUser;
import com.example.demo.model.DomainObject.NullPattern.NullUser;
import com.example.demo.model.DomainObject.User;
import com.example.demo.model.Exception.UnFoundResultData;
import com.example.demo.model.Persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Enum.UserEnum;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    //functie de inregistrare
    //adauga logica de criptare a parolei
    @Override
    public void saveUser(AbstractUser newUser){
        //newUser.setType(UserEnum.simple);
        userRepository.save((User)newUser);
    }

    @Override
    public AbstractUser findUserByName(String name) {
        User user = userRepository.findByName(name);
        if(null == user){
            return new NullUser();
        }
        return user;
    }

    @Override
    public AbstractUser findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(null == user){
            return new NullUser();
        }
        return user;
    }

    @Override
    public AbstractUser findUserById(int id) {
        User user = userRepository.findById(id);
        if(null == user){
            return new NullUser();
        }
        return user;
    }

    @Override
    public AbstractUser findUserByEmailAndPassword(String email, String password) {
        AbstractUser user = userRepository.findByEmailAndPassword(email, password);
        if(null == user) {
            return new NullUser();
        }
        return user;
    }

    @Override
    public void deleteUserById(int id) throws UnFoundResultData {
        if(null == userRepository.findById(id)){
            throw new UnFoundResultData();
        }
        userRepository.deleteById(id);

    }

    @Override
    public void updateUser(int id, User newUser) throws UnFoundResultData{
        System.out.println(id);
        User oldUser = userRepository.findById(id);
        System.out.println(oldUser);
        if(null == oldUser){
            throw new UnFoundResultData();
        }

        oldUser.setName(newUser.getName());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setType(UserEnum.simple);

        userRepository.save(oldUser);
    }

    @Override
    public AbstractUser whichUserIsLoggedIn() {
        return userRepository.findByIsLogged(1);
    }
}
