package com.it.web.sad.itwebsad.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "comments")
public class CommentEntity {

    @NotNull
    private String id;

    @NotNull
    private String storyId;

    @NotNull
    private String type;

    @NotNull
    private String version;

    @NotNull
    private String message;

    private String image;

    private String isSend;

    @NotNull
    private String time;

    @NotNull
    private UserEntity user;

}
