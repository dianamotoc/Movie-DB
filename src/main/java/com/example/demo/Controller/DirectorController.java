package com.example.demo.Controller;

import com.example.demo.model.DomainObject.Director;
import com.example.demo.model.Exception.UnFoundResultData;
import com.example.demo.model.Service.DirectorService;
import com.example.demo.model.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DirectorController {

    @Autowired
    DirectorService directorService;

    @Autowired
    UserService userService;

    @GetMapping("/findDirectorByName")
    public String findDirectorByName(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("isLoggedIn", userService.whichUserIsLoggedIn() != null);
        Director director =directorService.findDirectorByName(name);
        model.addAttribute("director", director);
        return "index";
    }

    @PostMapping("/addDirector")
    public String addDirector(@ModelAttribute Director director, Model model){
        model.addAttribute("isLoggedIn", userService.whichUserIsLoggedIn() != null);
        directorService.saveDirector(director);
        return "index";
    }

    @GetMapping("/allDirectors")
    public String allDirectors(Model model){
        model.addAttribute("isLoggedIn", userService.whichUserIsLoggedIn() != null);
        for(Director a : directorService.findAllDirectors()){
            System.out.println(a);
        }
        model.addAttribute("directors", directorService.findAllDirectors());
        return "directorsList";
    }

    @GetMapping("/directorForm")
    public String directorForm(Model model) {
        model.addAttribute("isLoggedIn", userService.whichUserIsLoggedIn() != null);
        model.addAttribute("director", new Director());
        return "directorForm";
    }

    @GetMapping("/director")
    public String directorTemplate(Model model, @RequestParam(name = "id", required = true) int id){
        model.addAttribute("isLoggedIn", userService.whichUserIsLoggedIn() != null);
        Director director = directorService.findDirectorById(id);
        model.addAttribute("director",director);
        System.out.println(director.getName());
        return "directorTemplate";
    }

    @GetMapping("/deleteDirector")
    public String deleteDirectorById(@RequestParam(name="id", required=false, defaultValue="World") int id){
        try {
            directorService.deleteDirectorById(id);
        }
        catch (UnFoundResultData e){
            return e.redirect_page();
        }
        return "directorForm";
    }

    @PostMapping("/updateDirector")
    public String updateDirector(@ModelAttribute Director director, @RequestParam(name = "id", required = true) int id){
        directorService.updateDirector(id,director);
        return "index";
    }

    @GetMapping("/updateDirectorForm")
    public String updateDirectorForm(Model model, @RequestParam(name = "id", required = true) int id)
    {
        model.addAttribute("isLoggedIn", userService.whichUserIsLoggedIn() != null);
        Director oldDirector =  directorService.findDirectorById(id);
        model.addAttribute("oldDirector", oldDirector);
        return "directorUpdateForm";
    }
}
