package com.it.web.sad.itwebsad.controller;


import com.it.web.sad.itwebsad.entity.Comment;
import com.it.web.sad.itwebsad.entity.ErrorResponse;
import com.it.web.sad.itwebsad.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
@Tag(name = "it-web-sad", description = "it-web-sad API")
@RestController
//@RequestMapping("/comments")
public class Controller {
    @Autowired
    private CommentService commentService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/comments")
    @Operation(summary = "get comment List - all", responses = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = Comment.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
    public List<Comment> getComments(){
        return commentService.getComments();
    }

    @PostMapping("/comments")
    @Operation(summary = "add comment")
    public ResponseEntity<Comment> postComment(@RequestBody Comment comment){
        Comment newComment = commentService.addComment(comment);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                                .buildAndExpand(newComment.getId())
                                        .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/comment/{commentId}")
    @Operation(summary = "get comment - by commentId")
    public Optional<Comment> getCommentById(@Parameter(name = "commentId", description = "comment 의 id", in = ParameterIn.PATH) @PathVariable String commentId){
        return commentService.getCommentById(commentId);
    }

    @PutMapping("/comment/{commentId}")
    @Operation(summary = "modify comment")
    public ResponseEntity<Comment> updateComment(@PathVariable String commentId, @RequestBody Comment comment){
        commentService.updateComment(commentId, comment);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/comment/{commentId}")
    @Operation(summary = "delete comment")
    public ResponseEntity<Comment> delete(@PathVariable String commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comments/{storyId}")
    @Operation(summary = "get comment List - by storyId", description = "get comment list by storyId")
    public List<Comment> getCommentsByStoryId(@PathVariable String storyId){
        return commentService.getCommentsByStoryId(storyId);
    }

}
