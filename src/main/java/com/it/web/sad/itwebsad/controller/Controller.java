package com.it.web.sad.itwebsad.controller;


import com.it.web.sad.itwebsad.dto.CommentDTO;
import com.it.web.sad.itwebsad.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private CommentService commentService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/comments")
    public List<CommentDTO> getComments(){
        return commentService.getComments();
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentDTO> postComment(@RequestBody CommentDTO commentDTO){
        CommentDTO savedComment = commentService.addComment(commentDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                                .buildAndExpand(savedComment.getId())
                                        .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/comment/{commentId}")
    public CommentDTO getCommentById(@PathVariable String commentId){
        return commentService.getCommentById(commentId);
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable String commentId, @RequestBody CommentDTO commentDTO){
        commentService.updateComment(commentId, commentDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<CommentDTO> delete(@PathVariable String commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comments/{storyId}")
    public List<CommentDTO> getCommentsByStoryId(@PathVariable String storyId){
        return commentService.getCommentsByStoryId(storyId);
    }

}
