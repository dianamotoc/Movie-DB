package com.example.demo.model.Service;

import com.example.demo.model.DomainObject.Comment;
import com.example.demo.model.Exception.UnFoundResultData;
import com.example.demo.model.Persistance.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public void saveComment(Comment newComment) {
        commentRepository.save(newComment);
    }

    @Override
    public void deleteCommentById(int id) throws UnFoundResultData{
        if(null == commentRepository.findById(id)){
            throw new UnFoundResultData();
        }
        commentRepository.deleteById(id);
    }

    @Override
    public void updateComment(int id, Comment newComment) {
        Comment oldComment = commentRepository.findById(id);
        if(null == oldComment){
            throw new UnFoundResultData();
        }
        oldComment.setText(newComment.getText());

        commentRepository.save(oldComment);
    }

    @Override
    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findCommentById(int id) {
        return commentRepository.findById(id);
    }
}
