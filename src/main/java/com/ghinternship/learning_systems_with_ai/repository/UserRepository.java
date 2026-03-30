package com.ghinternship.learning_systems_with_ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ghinternship.learning_systems_with_ai.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
