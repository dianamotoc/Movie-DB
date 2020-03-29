package com.example.demo.Controller;

import com.example.demo.model.Exception.UnFoundResultData;
import com.example.demo.model.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Service.ActorService;
import com.example.demo.model.DomainObject.Actor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ActorController {

    @Autowired
    ActorService actorService;

    @Autowired
    UserService userService;

    @GetMapping("/findActorByName")
    public String findActorByName(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        Actor actor =actorService.findActorByName(name);
        model.addAttribute("actor", actor);
        return "greeting";
    }

    @PostMapping("/addActor")
    public String addActor(@ModelAttribute Actor actor, @RequestParam(name="file") MultipartFile file, Model model){
        model.addAttribute("isLoggedIn", userService.whichUserIsLoggedIn() != null);
        String paths[] = new String[3];
        paths[0] = "https://m.media-amazon.com/images/M/MV5BMTk5Mzc4ODU0Ml5BMl5BanBnXkFtZTcwNjU1NTI0Mw@@._V1_UY317_CR12,0,214,317_AL_.jpg";
        paths[1] = "https://m.media-amazon.com/images/M/MV5BMTY0OTY3ODA3OV5BMl5BanBnXkFtZTcwMzMyMzQ1NQ@@._V1_SY1000_CR0,0,877,1000_AL_.jpg";
        paths[2] = "https://m.media-amazon.com/images/M/MV5BMjMxNzk1MTQyMl5BMl5BanBnXkFtZTgwMDIzMDEyMTE@._V1_SY1000_CR0,0,665,1000_AL_.jpg";

        model.addAttribute("isLoggedIn", userService.whichUserIsLoggedIn() != null);
        //String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        Path fileNameAndPath = Paths.get(actorService.IMAGE_PATH,actor.getName() + ".jpg");
        try {
            Files.write(fileNameAndPath,file.getBytes());
        }
        catch(IOException e)
        {
            System.out.println("Eroare bro");
        }

        if(actor.getName().equals("Charlize Theron"))
            actor.setPathToImage(paths[0]);
        else if(actor.getName().equals("Uma Thurman"))
            actor.setPathToImage(paths[2]);
        else if(actor.getName().equals("Jennifer Lopez"))
            actor.setPathToImage(paths[1]);
        actorService.saveActor(actor);
        return "actorForm";
    }

    @GetMapping("/allActors")
    public String allActors(Model model){
        model.addAttribute("isLoggedIn", userService.whichUserIsLoggedIn() != null);
        for(Actor a : actorService.findAllActors()){
            System.out.println(a);
        }
        model.addAttribute("actors",actorService.findAllActors());
        return "actorsList";
    }

    // aicipt useri
    @GetMapping("/listActors")
    public String listActors(Model model){
        model.addAttribute("isLoggedIn", userService.whichUserIsLoggedIn() != null);
        model.addAttribute("actors",actorService.findAllActors());
        return "listActor";
    }

    @GetMapping("/actor")
    public String actorTemplate(Model model, @RequestParam(name = "id", required = true) int id){
        Actor actor = actorService.findActorById(id);
        model.addAttribute("actor",actor);
        return "actorTemplate";
    }

    @GetMapping("/actorForm")
    public String actorForm(Model model) {
        model.addAttribute("actor", new Actor());
        return "actorForm";
    }

    @GetMapping("/updateActorForm")
    public String updateActorForm(Model model, @RequestParam(name = "id", required = true) int id)
    {
        Actor oldActor =  actorService.findActorById(id);
        model.addAttribute("oldActor", oldActor);
        return "actorUpdateForm";
    }

    @GetMapping("/deleteActor")
    public String deleteActorById(@RequestParam(name="id", required=false, defaultValue="World") int id){
        try {
            actorService.deleteActorById(id);
        }
        catch (UnFoundResultData e){
            return e.redirect_page();
        }
        return "redirect:allActors";
    }

    @PostMapping("/updateActor")
    public String updateActor(@ModelAttribute Actor actor, @RequestParam(name = "id", required = true) int id){
        actorService.updateActor(id,actor);
        return "index";
    }
}