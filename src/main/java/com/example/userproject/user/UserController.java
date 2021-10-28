package com.example.userproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @GetMapping()
    public String index(Model model) {
        Iterable iter = repository.findAll();
        model.addAttribute("users", iter);
        return "users";
    }

    @GetMapping(path = "{userId}")
    public String getSingleUser(@PathVariable("userId") Long userId, Model model) {
        Optional<User> option = repository.findById(userId);
        if (option.isPresent()) {
            model.addAttribute("user", option.get());
            model.addAttribute("projecten", option.get().getProjects());
            model.addAttribute("age", userService.getAge(option));
            return "specificUser";
        } else {
            return "redirect:/users";
        }
    }

    @PostMapping()
    public String postIndex(@ModelAttribute User user) {
        repository.save(user);
        return "redirect:/users";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute User passedUser) {
        userService.updateIfPresent(passedUser);
        return String.format("redirect:/users/%s", passedUser.getId());
    }

    @GetMapping("/removeUser")
    public String removeUser(@RequestParam(name = "id") Long id) {
        userService.removeIfPresent(id);
        return "redirect:/users";
    }
}
