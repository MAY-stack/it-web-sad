package com.it.web.sad.itwebsad.service;

import com.it.web.sad.itwebsad.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    /* all */
    public List<Comment> getComments();

    /* post comment */
    public Comment addComment(Comment comment);

    /* get by id */
    public Optional<Comment> getCommentById(String id);

    /* update */
    public void updateComment(String id, Comment comment);

    /* delete */
    public void deleteComment(String id);

    /* get by storyId */
    List<Comment> getCommentsByStoryId(String storyId);

}