package com.example.userproject.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class ProjectConfig {
/*
    @Bean
    CommandLineRunner commandLineRunner(ProjectRepository repository) {
        return args -> {
            Project test = new Project(
                    "Test Project",
                    LocalDate.of(2021, OCTOBER, 16),
                    "This is a description",
                    false,
                    6
            );

            Project test2 = new Project(
                    "Test Project numerous dos",
                    LocalDate.of(2021, NOVEMBER, 22),
                    "This is a description, again",
                    true,
                    9
            );

            repository.saveAll(
                    List.of(test, test2)
            );
        };
    }
*/
}
