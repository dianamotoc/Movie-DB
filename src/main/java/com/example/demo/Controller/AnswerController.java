package com.example.demo.Controller;

import com.example.demo.model.DomainObject.Answer;
import com.example.demo.model.Exception.UnFoundResultData;
import com.example.demo.model.Service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping("/addAnswer")
    public String addAnswer(@ModelAttribute Answer answer){
        answerService.saveAnswer(answer);
        return "index";
    }

    @GetMapping("/answerForm")
    public String answerForm() {
        return "answerForm";
    }



    @GetMapping("/deleteAnswer")
    public String deleteAnswerById(@RequestParam(name="id", required=false, defaultValue="World") int id){
        try {
            answerService.deleteAnswerById(id);
        }
        catch (UnFoundResultData e){
            return e.redirect_page();
        }
        return "answerForm";
    }

    @PostMapping("/updateAnswer")
    public String updateAnswer(@ModelAttribute Answer answer, @RequestParam(name = "id", required = true) int id){
        answerService.updateAnswer(id,answer);
        return "index";
    }
}