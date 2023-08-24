package com.it.web.sad.itwebsad.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @NotNull
    private String uuid;

    @NotNull
    private String name;

    private String ip;

    private String agent;

}
