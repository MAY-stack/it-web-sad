package com.it.web.sad.itwebsad.repository;


import com.it.web.sad.itwebsad.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByStoryId(String storyId);
}
