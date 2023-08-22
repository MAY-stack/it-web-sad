package com.it.web.sad.itwebsad.service.Impl;

import com.it.web.sad.itwebsad.entity.CommentEntity;
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
    public List<CommentEntity> getComments() {
        return commentRepository.findAll();
    }

    /* post comment */
    @Override
    public CommentEntity addComment(CommentEntity commentEntity) {
        return commentRepository.save(commentEntity);
    }

    /* get by id */
    @Override
    public Optional<CommentEntity> getCommentById(String id){
        return commentRepository.findById(id);
    }

    /* update */
    @Override
    public void updateComment(String id, CommentEntity commentEntity) {
        if(commentRepository.findById(id).isPresent()){
           CommentEntity targetComment = commentRepository.findById(id).get();
           targetComment.setMessage(commentEntity.getMessage());
           targetComment.setImage(commentEntity.getImage());
           targetComment.setType(commentEntity.getType());
           targetComment.setTime(commentEntity.getTime());
           targetComment.setUser(commentEntity.getUser());
           targetComment.setIsSend(commentEntity.getIsSend());

           commentRepository.save(targetComment);

        }
    }

    /* delete */
    @Override
    public void deleteComment(String id) {
        if(commentRepository.findById(id).isPresent()){
            CommentEntity targetComment = commentRepository.findById(id).get();
            commentRepository.delete(targetComment);
        }
    }

    /* get by storyId */
    @Override
    public List<CommentEntity> getCommentsByStoryId(String storyId){
        return commentRepository.findAllByStoryId(storyId);
    }

}