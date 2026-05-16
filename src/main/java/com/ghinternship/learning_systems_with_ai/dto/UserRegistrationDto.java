package com.ghinternship.learning_systems_with_ai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//import lombok.Getter;
//import lombok.Setter;


//@Setter
//@Getter

public class UserRegistrationDto {
    @NotBlank(message = "Username is required")
    @Size(min = 6, max = 20,
          message = "Username must be between 6 and 20 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8,
          message = "Password must be at least 8 characters long")
    private String password;

    public UserRegistrationDto() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}