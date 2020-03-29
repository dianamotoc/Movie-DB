package com.example.demo.Controller;

import com.example.demo.model.DomainObject.Award;
import com.example.demo.model.Exception.UnFoundResultData;
import com.example.demo.model.Service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AwardController {

    @Autowired
    AwardService awardService;

    @GetMapping("/findAwardByName")
    public String findAwardByName(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        Award award =awardService.findAwardByName(name);
        model.addAttribute("award", award);
        return "index";
    }

    @PostMapping("/addAward")
    public String addAward(@ModelAttribute Award award){
        awardService.saveAward(award);
        return "index";
    }

    @GetMapping("/awardForm")
    public String awardForm(Model model)
    {
        model.addAttribute("award", new Award());
        return "awardForm";
    }

    @GetMapping("/deleteAward")
    public String deleteAwardById(@RequestParam(name="id", required=false, defaultValue="World") int id){
        try {
            awardService.deleteAwardById(id);
        }
        catch (UnFoundResultData e){
            return e.redirect_page();
        }
        return "awardForm";
    }

    @PostMapping("/updateAward")
    public String updateAward(@ModelAttribute Award award, @RequestParam(name = "id", required = true) int id){
        awardService.updateAward(id,award);
        return "index";
    }

    @GetMapping("/updateAwardForm")
    public String updateAwardForm(Model model, @RequestParam(name = "id", required = true) int id)
    {
        Award oldAward =  awardService.findAwardById(id);
        model.addAttribute("oldAward", oldAward);
        return "awardUpdateForm";
    }
}