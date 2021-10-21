package com.example.userproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return (List<User>) userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository
                .findByUserEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("Email is already taken");
        }
        userRepository.save(user);
    }

    public void removeIfPresent(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
        }
    }

    public void updateIfPresent(User passedUser) {
        Optional<User> optionalUser = userRepository.findById(passedUser.getId());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setName(passedUser.getName());
            existingUser.setEmail(passedUser.getEmail());
            userRepository.save(existingUser);
        }
    }

}
