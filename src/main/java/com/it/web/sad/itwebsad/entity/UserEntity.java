package com.it.web.sad.itwebsad.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {

    @NotNull
    private String uuid;

    @NotNull
    private String name;

    private String ip;

    private String agent;

}
