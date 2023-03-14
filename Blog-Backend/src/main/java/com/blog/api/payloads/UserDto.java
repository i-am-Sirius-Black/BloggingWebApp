package com.blog.api.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 4,message = "Username must be greater than 4 characters !!")
    private String name;
    @Email(message = "Email address is not valid !!")
    private String email;
    @NotEmpty

    @Size(min = 3,max = 15,message = "Password must be min 3 and max 15 char !!")
    private String password;
    @NotEmpty
    private String about;

    private Set<RoleDto> roles = new HashSet<>();

}
