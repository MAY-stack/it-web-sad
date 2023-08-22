package com.it.web.sad.itwebsad.controller;


import org.slf4j.Logger;
import com.it.web.sad.itwebsad.entity.CommentEntity;
import com.it.web.sad.itwebsad.service.CommentService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/comments")
public class Controller {
    @Autowired
    private CommentService commentService;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @GetMapping("/comments")
    public List<CommentEntity> getComments(){
        return commentService.getComments();
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentEntity> postComment(@RequestBody CommentEntity commentEntity){
        CommentEntity newCommentEntity = commentService.addComment(commentEntity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                                .buildAndExpand(newCommentEntity.getId())
                                        .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/comment/{commentId}")
    public Optional<CommentEntity> getCommentById(@PathVariable String commentId){
        return commentService.getCommentById(commentId);
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<CommentEntity> updateComment(@PathVariable String commentId, @RequestBody CommentEntity commentEntity){
        commentService.updateComment(commentId, commentEntity);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<CommentEntity> delete(@PathVariable String commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comments/{storyId}")
    public List<CommentEntity> getCommentsByStoryId(@PathVariable String storyId){
        return commentService.getCommentsByStoryId(storyId);
    }

}
