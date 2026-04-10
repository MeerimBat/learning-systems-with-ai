package com.ghinternship.learning_systems_with_ai;

import com.ghinternship.learning_systems_with_ai.dto.UserRegistrationDto;
import com.ghinternship.learning_systems_with_ai.model.User;
import com.ghinternship.learning_systems_with_ai.repository.UserRepository;
import com.ghinternship.learning_systems_with_ai.service.UserService;
import com.ghinternship.learning_systems_with_ai.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class) //Enables Mockito annotations
class UserServiceImplTest {

    // Mock the dependency
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    // Inject mock into UserServiceImpl (via constructor)
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void RegisterUserSuccessfully() {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setUsername("Bebe");
        dto.getPassword("123");

        //given(userRepository).findByUsername("Bebe");
             // .willReturn(Optional.empty());
        when(userRepository.findByUsername("Bebe")).thenReturn(Optional.empty());

        when(passwordEncoder.encode("123")).thenReturn("encoded123");

        when(UserRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArguments(0));

        User result = UserService.registerUser(dto);

        assertEquals("Bebe"),result.getUsername());
        assertEquals("encoded123",result.getPasswordHash());

        verify(userRepository).save(any(User.class));
    }
    @Test
    void ExceptionWhenUserNameExists() {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setUsername("Bebe");

        when(userRepository.findByUsername("Bebe")).thenReturn(Optional.of(new User()));

        RuntimeException ex =assertThrows(RuntimeException.class);
            () -> userService.registerUser(dto));

        assertEquals("Username already exists!", ex.getMessage());

        verify(userRepository, never()).save(any());
    }
    @Test
    void FindByUsername(); {
         User user = new User();
         user.setUsername("Bebe");

         when(userRepository.findByUsername("Bebe")).thenReturn(Optional.of(user));

         User result = userService.findByUsername("Bebe");

         assertEquals("Bebe", result.getUsername());
    }
    @Test
    void ExceptionWhenUserNotFound() {
        when(userRepository.findByUsername("Bebe")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,() -> userService.findByUsername("Bebe"));

        assertEquals("User not found", ex.getMessage());
    }
}

