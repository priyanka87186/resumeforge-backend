package com.resumeforge.backend.controller;

import com.resumeforge.backend.payload.request.RegisterRequest;
import com.resumeforge.backend.payload.response.UserResponse;
import com.resumeforge.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest userRequest) {
        // User creation logic here
        UserResponse userResponse = userService.register(userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable String id) throws Exception {
        // Logic to get user by ID
        return ResponseEntity.ok(Map.of());

    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        // Logic to get all users
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(Map.of("users", users,
                "count", users.size(),
                "success", true,
                "message", "Users retrieved successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable String id, @RequestBody RegisterRequest userRequest) throws Exception {
        // Logic to update user
        return ResponseEntity.ok((Map<String, Object>) userService.updateUser(id, userRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable String id) throws Exception {
        // Logic to delete user
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
