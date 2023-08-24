package com.it.web.sad.itwebsad.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.web.sad.itwebsad.dto.CommentDTO;
import com.it.web.sad.itwebsad.service.CommentService;
import dev.harrel.jsonschema.ValidatorFactory;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {
    @Autowired
    private CommentService commentService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/comments")
    public List<CommentDTO> getComments() throws Exception{
        return commentService.getComments();
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentDTO> postComment(@Valid @RequestBody CommentDTO commentDTO) throws Exception {
        CommentDTO savedComment = commentService.addComment(commentDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                                .buildAndExpand(savedComment.getId())
                                        .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/comment/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable("commentId") String id) throws Exception {
        if(commentValidator(commentService.getCommentById(id))){
            return ResponseEntity.ok(commentService.getCommentById(id));
        } else return ResponseEntity.noContent().build();
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@Valid @PathVariable String commentId, @RequestBody CommentDTO commentDTO) throws Exception {
        commentService.updateComment(commentId, commentDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<CommentDTO> delete(@PathVariable String commentId) throws Exception {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comments/{storyId}")
    public List<CommentDTO> getCommentsByStoryId(@PathVariable String storyId) throws Exception {
        return commentService.getCommentsByStoryId(storyId);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String,String>> ExceptionHandler(Exception e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Map<String,String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }

    public boolean commentValidator(CommentDTO commentDTO) throws Exception {

        String schema = new String(Files.readAllBytes(Paths.get("D:\\Dropbox\\MySource\\SpringBoot\\it-web-sad\\src\\main\\resources\\comment-schema.json")));

        ObjectMapper objectMapper = new ObjectMapper();
        String instance = objectMapper.writeValueAsString(commentDTO);

        boolean valid = new ValidatorFactory().validate(schema, instance).isValid();
        logger.info(String.valueOf(valid));
        return valid;
    }
}
