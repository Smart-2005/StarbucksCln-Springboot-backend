package com.projects.secondproject.dto.request;

import com.projects.secondproject.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestUserDto {

    private String name;
    private String username;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;

}
