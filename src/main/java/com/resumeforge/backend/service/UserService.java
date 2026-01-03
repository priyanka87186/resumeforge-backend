package com.resumeforge.backend.service;

import com.resumeforge.backend.payload.request.RegisterRequest;
import com.resumeforge.backend.payload.response.UserResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface UserService {

    UserResponse register(RegisterRequest userRequest);

    List<UserResponse> getAllUsers();

    @Nullable UserResponse updateUser(String id, RegisterRequest registerRequest) throws Exception;

    @Nullable UserResponse getUserById(String id) throws Exception;

    void deleteUser(String id) throws Exception;
}
