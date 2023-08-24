package com.it.web.sad.itwebsad.dto;

import com.it.web.sad.itwebsad.entity.CommentEntity;
import com.it.web.sad.itwebsad.entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {
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

    public CommentDTO (CommentEntity commentEntity) {
        this.id = commentEntity.getId();
        this.storyId = commentEntity.getStoryId();
        this.type = commentEntity.getType();
        this.version = commentEntity.getVersion();
        this.message = commentEntity.getMessage();
        this.image = commentEntity.getImage();
        this.isSend = commentEntity.getIsSend();
        this.time = commentEntity.getTime();
        this.user = commentEntity.getUser();
    }

    public CommentEntity dtoToEntity(CommentDTO commentDTO){
        return CommentEntity.builder()
                .id(commentDTO.getId())
                .storyId(commentDTO.getStoryId())
                .type(commentDTO.getType())
                .version(commentDTO.getVersion())
                .message(commentDTO.getMessage())
                .image(commentDTO.getImage())
                .isSend(commentDTO.getIsSend())
                .time(commentDTO.getTime())
                .user(commentDTO.getUser())
                .build();
    }

}
