package com.example.userproject.user;

import com.example.userproject.project.Project;
import com.example.userproject.project.ProjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;

@Configuration
public class UserConfig {
    /*
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User userTest = new User(
                    "Quintine Eier",
                    "quintine_eier@hotmail.com",
                    LocalDate.of(1995, Month.JULY, 29),
                    26
            );

            User userTest2 = new User(
                    "Quintine Eggerinos",
                    "PerkeleQuinto@hotmail.com",
                    LocalDate.of(1989, Month.AUGUST, 18),
                    26
            );

            repository.saveAll(
                    List.of(userTest, userTest2)
            );
        };
    }

     */
}
