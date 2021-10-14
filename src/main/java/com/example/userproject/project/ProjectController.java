package com.example.userproject.project;

import com.example.userproject.user.User;
import com.example.userproject.user.UserRepository;
import com.example.userproject.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model) {
        Iterable iter = projectService.getProjects();
        model.addAttribute("projects", iter);
        return "projects";
    }

    @PostMapping
    public String postIndex(@ModelAttribute AddProjectCommand projectCommand) {
        Optional<User> optionalUser = userService.findById(projectCommand.getUserId());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            Project project = new Project();
            project.setProjectName(projectCommand.getProjectName());
            project.setDeadline(projectCommand.getDeadline());
            project.setDescription(projectCommand.getDescription());
            project.setPriority(projectCommand.getPriority());
            project.setDeadline(projectCommand.getDeadline());
            project.setUser(user);
            user.getProjects().add(project);
            userService.save(user);
        }

        return "redirect:/projects";
    }
}
