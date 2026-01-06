package com.resumeforge.backend.entities;

import com.resumeforge.backend.enums.Role;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    private String id;

    @Email
    @Indexed(unique = true)
    private String email;

    private String password;

    private LocalDate createdAt = LocalDate.now();

    private LocalDate updatedAt = LocalDate.now();

    private String subscriptionStatus = "FREE";

    private List<String> resumeIds = new ArrayList<>();

    @Field(targetType = FieldType.STRING)
    private Role role;

    private  String firstName;
    private  String lastName;

}
