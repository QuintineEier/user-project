package com.example.userproject.user;

import com.example.userproject.project.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String>{

    @Query("SELECT s FROM User s WHERE s.email = ?1")
    Optional<User> findByUserEmail(String email);

    boolean existsById(Long userId);

    Optional<User> findById(Long userId);
}