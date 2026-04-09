package com.ghinternship.learning_systems_with_ai.dto;

//import lombok.Getter;
//import lombok.Setter;

//@Setter
//@Getter
public class UserRegistrationDto {
    private String username;
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