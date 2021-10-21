package com.example.userproject.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>{

    @Query("SELECT s FROM User s WHERE s.email = ?1")
    Optional<User> findByUserEmail(String email);

}