package com.resumeforge.backend.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private String id;
    private String email;
    private String subscriptionStatus;
    private List<String> resumeIds = new ArrayList<>();
}
