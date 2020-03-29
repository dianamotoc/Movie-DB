package com.example.demo.Controller;

import com.example.demo.model.DomainObject.Question;
import com.example.demo.model.Exception.UnFoundResultData;
import com.example.demo.model.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("/addQuestion")
    public String addQuestion(@ModelAttribute Question question){
        questionService.saveQuestion(question);
        return "index";
    }

    @GetMapping("/questionForm")
    public String questionForm(Model model)
    {
        model.addAttribute("question", new Question());
        return "questionForm";
    }

    @GetMapping("/allQuestions")
    public String allQuestions(Model model){
        for(Question a : questionService.findAllQuestions()){
            System.out.println(a);
        }
        model.addAttribute("questions",questionService.findAllQuestions());
        return "questionList";
    }

    @GetMapping("/question")
    public String questionTemplate(Model model, @RequestParam(name = "id", required = true) int id){
        Question question = questionService.findQuestionById(id);
        model.addAttribute("question",question);
        return "questionTemplate";
    }

    @GetMapping("/deleteQuestion")
    public String deleteQuestionById(@RequestParam(name="id", required=false, defaultValue="World") int id){
        try {
            questionService.deleteQuestionById(id);
        }
        catch (UnFoundResultData e){
            return e.redirect_page();
        }
        return "questionForm";
    }

    //modifica aici return
    @PostMapping("/updateQuestion")
    public String updateQuestion(@ModelAttribute Question question, @RequestParam(name = "id", required = true) int id){
        questionService.updateQuestion(id,question);
        return "index";
    }

    @GetMapping("/updateQuestionForm")
    public String updateQuestionForm(Model model, @RequestParam(name = "id", required = true) int id)
    {
        Question oldQuestion =  questionService.findQuestionById(id);
        model.addAttribute("oldQuestion", oldQuestion);
        return "questionUpdateForm";
    }
}