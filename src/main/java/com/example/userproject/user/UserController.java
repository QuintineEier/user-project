package com.example.userproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping()
    public String index(Model model) {
        Iterable iter = repository.findAll();
        model.addAttribute("users", iter);
        return "users";
    }

    @PostMapping()
    public String postIndex(@ModelAttribute User user) {
        repository.save(user);
        return "redirect:/users";
    }
}
