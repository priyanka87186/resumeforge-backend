package com.resumeforge.backend.service;

import com.resumeforge.backend.entities.User;
import com.resumeforge.backend.exception.ResourceNotFoundException;
import com.resumeforge.backend.exception.UnauthorizedException;
import com.resumeforge.backend.payload.request.LoginRequest;
import com.resumeforge.backend.payload.response.AuthResponse;
import com.resumeforge.backend.repository.UserRepository;
import com.resumeforge.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest loginRequest) {

        Optional<User> existingUser = userRepository.findByEmail(loginRequest.getEmail());
        if(existingUser.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        User user = existingUser.get();

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new UnauthorizedException("Invalid password");
        }

        String token = jwtUtil.generateToken(loginRequest.getEmail(), user.getRole().name().toLowerCase());


       return new AuthResponse( token, user.getEmail(), user.getSubscriptionStatus(), user.getRole().toString());
    }


}
