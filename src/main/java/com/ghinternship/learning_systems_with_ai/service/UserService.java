package com.ghinternship.learning_systems_with_ai.service;

import com.ghinternship.learning_systems_with_ai.dto.UserRegistrationDto;
import com.ghinternship.learning_systems_with_ai.model.User;

public interface UserService {
    User registerUser(UserRegistrationDto userData);

    User findByUsername(String username);
}
