package com.example.demo.model.Service;
import com.example.demo.model.DomainObject.Comment;

import java.util.List;

public interface CommentService extends AbstractService {
    void saveComment(Comment newComment);
    void deleteCommentById(int id);
    void updateComment(int id, Comment newComment);
    List<Comment> findAllComments();
    //List<Comment> findAllByMovieId(int id);

    Comment findCommentById(int id);
}
