package com.example.userproject.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static java.time.Month.JULY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    private ProjectService service;

    @MockBean
    private ProjectRepository repository;

    @Test
    @DisplayName("Test find project by id with correct data")
    void testFindById() {
        Project projectTest = new Project(
                1l,
                "Dit is een project",
                LocalDate.of(1995, JULY, 29),
                "Dit is een beschrijving",
                false,
                9
        );
        when(repository.findById(1l)).thenReturn(Optional.of(projectTest));

        Optional<Project> returnedProject = repository.findById(1l);

        assertTrue(returnedProject.isPresent());
        assertEquals(returnedProject.get().getProjectName(), "Dit is een project");
        assertEquals(returnedProject.get().getDeadline(), LocalDate.of(1995, JULY, 29));
        assertEquals(returnedProject.get().getDescription(), "Dit is een beschrijving");
        assertEquals(returnedProject.get().getPriority(), 9);
    }


}