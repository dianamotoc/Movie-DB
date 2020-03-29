package com.example.demo.Controller;

import com.example.demo.model.DomainObject.NullPattern.AbstractUser;
import com.example.demo.model.DomainObject.User;
import com.example.demo.model.Enum.UserEnum;
import com.example.demo.model.Exception.UnFoundResultData;
import com.example.demo.model.Service.MovieService;
import com.example.demo.model.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Generated;
import java.sql.SQLIntegrityConstraintViolationException;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MovieService movieService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("isLoggedIn", isLoggedIn());
        model.addAttribute("movies",movieService.findAllMovies());
        System.out.println(isLoggedIn());
        return "index";
    }

    @PostMapping("/registerPost")
    public String addUser(@ModelAttribute User user, Model model){
        model.addAttribute("isLoggedIn", isLoggedIn());
        try
        {
            userService.saveUser(user);
        }
        catch (SQLIntegrityConstraintViolationException e){
            return "index";
            // fa ceva aici dar is prea obosit
        }
        return "index";
    }

    @GetMapping("/register")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("isLoggedIn", isLoggedIn());
        return "userForm";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute User user, @RequestParam(name = "id", required = true) int id, Model model){
        model.addAttribute("isLoggedIn", isLoggedIn());
        try
        {
            userService.updateUser(id, user);
        }
        catch(UnFoundResultData e){
            return e.redirect_page();
        }
        return "index";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam(name="id", required=false, defaultValue="World") int id, Model model)
    {
        try {
            userService.deleteUserById(id);
        }
        catch (UnFoundResultData e)
        {
            return e.redirect_page();
        }
        return "index";
    }

    @GetMapping("/findUserByEmail")
    public String findActorByEmail(@RequestParam(name="email", required=false, defaultValue="World") String email, Model model){
        AbstractUser user = userService.findUserByEmail(email);
        System.out.println(user);
        model.addAttribute("user", user);
        model.addAttribute("isLoggedIn", isLoggedIn());
        return "index";
    }

    @GetMapping("/findUserByName")
    public String findUserByName(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model){
        AbstractUser user = userService.findUserByName(name);
        System.out.println(user);
        model.addAttribute("user", user);
        model.addAttribute("isLoggedIn", isLoggedIn());
        return "index";
    }

    @GetMapping("/updateUserForm")
    public String updateUserForm(Model model, @RequestParam(name = "id", required = true) int id)
    {
        AbstractUser oldUser =  userService.findUserById(id);
        model.addAttribute("oldUser", oldUser);
        model.addAttribute("isLoggedIn", isLoggedIn());
        return "userUpdateForm";
    }

    @GetMapping("/adminDashboard")
    public String adminDashBoard(Model model){
        model.addAttribute("isLoggedIn", isLoggedIn());
        if(isAdmin())
            return "adminDashboard";
        return "dataNotFound";
    }

    @GetMapping("/login")
    public String login(Model model){
        if(isLoggedIn())
            return "redirect:/";
        model.addAttribute("user", new User());
        model.addAttribute("isLoggedIn", isLoggedIn());
        return "loginPage";
    }

    @PostMapping("/loginPost")
    public String loginPost(@ModelAttribute User user, Model model){
        model.addAttribute("isLoggedIn", isLoggedIn());
        if(isLoggedIn())
            return "redirect:/";
        AbstractUser loginUser =userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
        if(loginUser.isNull()){
            return "redirect:login";
        }
        loginUser.setIsLogged(1);
        try{
        userService.saveUser(loginUser);
        }
        catch(SQLIntegrityConstraintViolationException e){
            return "dataNotFound";
        }

        if(isAdmin())
            return "redirect:adminDashboard";
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(Model model){
        model.addAttribute("isLoggedIn", isLoggedIn());
        if(!isLoggedIn())
            return "redirect:/";
        AbstractUser user = userService.whichUserIsLoggedIn();
        user.setIsLogged(0);
        try
        {
            userService.saveUser(user);
        }catch (SQLIntegrityConstraintViolationException e){
            return "redirect:/";
        }
        return "redirect:/";
    }

    private boolean isLoggedIn() {
        Model model;
        return userService.whichUserIsLoggedIn() != null;
    }

    private boolean isAdmin(){
        AbstractUser user =  userService.whichUserIsLoggedIn();
        if(null == user)
            return false;
        return user.getType() == UserEnum.admin;
    }

    private boolean isCritic(){
        return userService.whichUserIsLoggedIn().getType() == UserEnum.critic;
    }

}