package com.resumeforge.backend.controller;

import com.resumeforge.backend.payload.request.RegisterRequest;
import com.resumeforge.backend.payload.request.LoginRequest;
import com.resumeforge.backend.payload.response.UserResponse;
import com.resumeforge.backend.repository.UserRepository;
import com.resumeforge.backend.service.AuthService;
import com.resumeforge.backend.service.UserService;
import com.resumeforge.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
          // Authentication logic here
         return ResponseEntity.ok(Map.of("user",authService.login(request)));
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest user) {

        return userService.register(user);
    }
}
