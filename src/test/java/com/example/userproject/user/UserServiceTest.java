package com.example.userproject.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static java.time.Month.JULY;
import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    @Test
    @DisplayName("Test find user by id with correct data")
    void testFindById() {
        User userTest = new User(
                1l,
                "Quintine Eier",
                "quintine_eier@hotmail.com",
                LocalDate.of(1995, JULY, 29),
                26
        );
        when(repository.findById(1l)).thenReturn(Optional.of(userTest));

        Optional<User> returnedUser = repository.findById(1l);

        assertTrue(returnedUser.isPresent());
        assertEquals(returnedUser.get().getName(), "Quintine Eier");
        assertEquals(returnedUser.get().getEmail(), "quintine_eier@hotmail.com");
        assertEquals(returnedUser.get().getAge(), 26);
        assertEquals(returnedUser.get().getDob(), LocalDate.of(1995, JULY, 29));
    }



}