package com.ghinternship.learning_systems_with_ai;

import com.ghinternship.learning_systems_with_ai.dto.UserRegistrationDto;
import com.ghinternship.learning_systems_with_ai.model.User;
import com.ghinternship.learning_systems_with_ai.repository.UserRepository;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional

public class AuthControllerTest {
}
