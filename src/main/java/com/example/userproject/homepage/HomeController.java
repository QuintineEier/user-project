package com.example.userproject.homepage;

import com.example.userproject.project.ProjectRepository;
import com.example.userproject.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.StreamSupport;

@Controller
public class HomeController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable iterProjects = projectRepository.findAll();
        Iterable iterUsers = userRepository.findAll();

        Long countProjects = StreamSupport.stream(iterProjects.spliterator(), false).count();
        Long countUsers = StreamSupport.stream(iterUsers.spliterator(), false).count();

        model.addAttribute("countProjects", countProjects);
        model.addAttribute("countUsers", countUsers);

        return "index";
    }
}
