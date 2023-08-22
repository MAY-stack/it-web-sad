package com.it.web.sad.itwebsad.service;

import com.it.web.sad.itwebsad.entity.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    /* all */
    public List<CommentEntity> getComments();

    /* post comment */
    public CommentEntity addComment(CommentEntity commentEntity);

    /* get by id */
    public Optional<CommentEntity> getCommentById(String id);

    /* update */
    public void updateComment(String id, CommentEntity commentEntity);

    /* delete */
    public void deleteComment(String id);

    /* get by storyId */
    List<CommentEntity> getCommentsByStoryId(String storyId);

}