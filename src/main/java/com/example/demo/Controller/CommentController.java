package com.example.demo.Controller;

import com.example.demo.model.DomainObject.Comment;
import com.example.demo.model.DomainObject.Movie;
import com.example.demo.model.Exception.UnFoundResultData;
import com.example.demo.model.Service.CommentService;
import com.example.demo.model.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    MovieService movieService;

    @PostMapping("/addComment")
    public String addComment(@ModelAttribute Comment comment){
        commentService.saveComment(comment);
        return "index";
    }

//    @GetMapping("/commentForm")
//    public String commentForm(Model model)
//    {
//        model.addAttribute("comment", new Comment());
//        return "commentForm";
//    }

    @GetMapping("/deleteComment")
    public String deleteCommentById(@RequestParam(name="id", required=false, defaultValue="World") int id){
        try {
            commentService.deleteCommentById(id);
        }
        catch (UnFoundResultData e){
            return e.redirect_page();
        }
        return "commentForm";
    }

    @PostMapping("/updateComment")
    public String updateCcomment(@ModelAttribute Comment comment, @RequestParam(name = "id", required = true) int id){
        commentService.updateComment(id,comment);
        return "index";
    }

    @GetMapping("/updateCommentForm")
    public String updateCommentForm(Model model, @RequestParam(name = "id", required = true) int id)
    {
        Comment oldComment =  commentService.findCommentById(id);
        model.addAttribute("oldComment", oldComment);
        return "commentUpdateForm";
    }
}
