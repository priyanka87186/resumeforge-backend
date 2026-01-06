package com.resumeforge.backend.User;

import com.resumeforge.backend.payload.request.RegisterRequest;
import com.resumeforge.backend.payload.response.UserResponse;
import com.resumeforge.backend.repository.UserRepository;
import com.resumeforge.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void createUser_success() {
        RegisterRequest userRequest = new RegisterRequest();
        userRequest.setEmail("test@gmail.com");
        userRequest.setPassword("password123");

        UserResponse userResponse = userService.register(userRequest);

        // Add assertions to verify the response
            assertNotNull(userResponse);
            assertEquals("test@gmail.com", userResponse.getEmail());
    }
}
