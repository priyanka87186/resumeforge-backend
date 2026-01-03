package com.resumeforge.backend.payload.request;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {
    @Valid
    private String email;
    private String password;
    private String subscriptionStatus = "FREE";
    private List<String> resumeIds = new ArrayList<>();
}
