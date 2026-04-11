package com.ghinternship.learning_systems_with_ai;

import com.ghinternship.learning_systems_with_ai.dto.UserRegistrationDto;
import com.ghinternship.learning_systems_with_ai.model.User;
import com.ghinternship.learning_systems_with_ai.repository.UserRepository;
import jdk.internal.jshell.tool.ConsoleIOContext;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional

public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldRegisterUserViaHttp() throws Exception {
        //given
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setUsername("Bebe");
        dto.setPassword("123");

        //when
        mockMvc.perform(post("/api/auth/register"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writevalueAsString(dto))
                .andExpect(status().isCreated())
                .andExpect(jasonPath("$.username").value("Bebe"));
        
    }

    }

    @Test
    void shouldReturnBadRequestWhenUsernameExists() throws Exception {
        // given (existing user)
        User user = new User();
        user.setUsername("Bebe");
        user.setPasswordHash("encoded");
        userRepository.save(user);

        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setUsername("Bebe");
        dto.setPassword("123");

        //when / then
        mockMvc.perform(post("/users"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Username already exists"));

    }
    @Test
    void shouldReturnUserViaHttp() throws Exception {
        User user = new User();
        user.setUsername("Bebe");
        user.setPasswordHash("encoded");
        userRepository.save(user);


        mockMvc.perform(get("/users/Bebe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Bebe"));
    }
    @Test
    void shouldReturn404WhenUserNotFound(){
        mockMvc.perform(get("/users/unknown"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("User not found"));
    }
}
