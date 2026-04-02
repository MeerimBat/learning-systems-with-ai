package com.ghinternship.learning_systems_with_ai.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegistrationDto {
    private String username;
    private String password;

    UserRegistrationDto() {
    }

}
