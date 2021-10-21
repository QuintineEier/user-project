package com.example.userproject.project;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Query("SELECT s FROM Project s WHERE s.projectName = ?1")
    Optional<Project> findByProjectName(String projectName);
}
