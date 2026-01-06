package com.resumeforge.backend.service.impl;

import com.resumeforge.backend.entities.User;
import com.resumeforge.backend.enums.Role;
import com.resumeforge.backend.exception.DuplicateResourceException;
import com.resumeforge.backend.payload.request.RegisterRequest;
import com.resumeforge.backend.payload.response.UserResponse;
import com.resumeforge.backend.repository.UserRepository;
import com.resumeforge.backend.service.UserService;
import jakarta.annotation.PostConstruct;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(RegisterRequest registerRequest) {
        UserResponse userResponse = new UserResponse();
        //check if email is unique
        if(userRepository.existsByEmail(registerRequest.getEmail())){
            throw new DuplicateResourceException("Email already exists");
        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword( passwordEncoder.encode(registerRequest.getPassword()));
        user.setSubscriptionStatus(registerRequest.getSubscriptionStatus());
        user.setResumeIds(registerRequest.getResumeIds());
        user.setRole(Role.USER);
        User savedUser = userRepository.save(user);
        return this.UsertoUserResponse(savedUser);
    }

    private UserResponse UsertoUserResponse(User savedUser) {

        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setSubscriptionStatus(savedUser.getSubscriptionStatus());
        userResponse.setResumeIds(savedUser.getResumeIds());
        return userResponse;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::UsertoUserResponse).toList();
    }

    @Override
    public @Nullable UserResponse updateUser(String id, RegisterRequest registerRequest) throws Exception {

        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
        user.setEmail(registerRequest.getEmail());
        user.setPassword( passwordEncoder.encode(registerRequest.getPassword()));
        user.setSubscriptionStatus(registerRequest.getSubscriptionStatus());
        user.setResumeIds(registerRequest.getResumeIds());
        User updatedUser = userRepository.save(user);
        return UsertoUserResponse(updatedUser);
    }

    @Override
    public @Nullable UserResponse getUserById(String id) throws Exception {
        if(!userRepository.existsById(id)) {
            throw new Exception("User not found with id: " + id);
        }
        return userRepository.findById(id).map(this::UsertoUserResponse).orElse(null);
    }

    @Override
    public void deleteUser(String id) throws Exception {
        if(!userRepository.existsById(id)) {
            throw new Exception("User not found");
        }
        userRepository.deleteById(id);
    }

    @PostConstruct
    public void insertAdminUser(){
        if(userRepository.existsByEmail("a@gmail.com")) {
            return;
        }
        User adminUser = new User();
        adminUser.setEmail("a@gmail.com");
        adminUser.setPassword(passwordEncoder.encode("admin123"));
        adminUser.setRole(Role.ADMIN);
        userRepository.save(adminUser);
    }

}
