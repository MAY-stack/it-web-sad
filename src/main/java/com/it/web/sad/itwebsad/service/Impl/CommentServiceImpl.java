package com.it.web.sad.itwebsad.service.Impl;

import com.it.web.sad.itwebsad.entity.Comment;
import com.it.web.sad.itwebsad.repository.CommentRepository;
import com.it.web.sad.itwebsad.service.CommentService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /* all */
    @Override
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    /* post comment */
    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    /* get by id */
    @Override
    public Optional<Comment> getCommentById(String id){
        return commentRepository.findById(id);
    }

    /* update */
    @Override
    public void updateComment(String id, Comment comment) {
        if(commentRepository.findById(id).isPresent()){
           Comment targetComment = commentRepository.findById(id).get();
           targetComment.setMessage(comment.getMessage());
           targetComment.setImage(comment.getImage());
           targetComment.setType(comment.getType());
           targetComment.setTime(comment.getTime());
           targetComment.setUser(comment.getUser());
           targetComment.setIsSend(comment.getIsSend());

           commentRepository.save(targetComment);

        }
    }

    /* delete */
    @Override
    public void deleteComment(String id) {
        if(commentRepository.findById(id).isPresent()){
            Comment targetComment = commentRepository.findById(id).get();
            commentRepository.delete(targetComment);
        }
    }

    /* get by storyId */
    @Override
    public List<Comment> getCommentsByStoryId(String storyId){
        return commentRepository.findAllByStoryId(storyId);
    }

}