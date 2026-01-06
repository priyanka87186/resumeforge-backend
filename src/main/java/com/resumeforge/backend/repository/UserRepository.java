package com.resumeforge.backend.repository;
import com.resumeforge.backend.entities.User;
import com.resumeforge.backend.enums.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    @Query(value = "{ 'email' : ?0 }", fields = "{ 'role' : 1 }")
    Role findRoleByEmail(String subject);
}
