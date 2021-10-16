package com.example.userproject.project;

import com.example.userproject.user.User;
import com.example.userproject.user.UserRepository;
import com.example.userproject.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public String index(Model model) {
        Iterable iter = projectService.getProjectsWithUsername();
        model.addAttribute("projects", iter);
        return "projects";
    }

    @GetMapping(path = "{projectId}")
    public String getSingleProject(@PathVariable("projectId") Long projectId, Model model) {
        Optional<Project> option = projectRepository.findById(projectId);
        if (option.isPresent()) {
            model.addAttribute("project", option.get());
            model.addAttribute("userName", option.get().getUser().getName());
            //model.addAttribute("userEmail", option.get().getUser().getEmail());
            model.addAttribute("userId", option.get().getUser().getId());
            model.addAttribute("projectId", projectId);
            return "specificProject";
        } else {
            return "redirect:/projects";
        }
        //Optional<User> optionUser = userRepository.findById()
    }

    @PostMapping
    public String postIndex(@ModelAttribute AddProjectCommand projectCommand) {
        Optional<User> optionalUser = userRepository.findById(projectCommand.getUserId());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            Project project = new Project();
            project.setProjectName(projectCommand.getProjectName());
            project.setDeadline(projectCommand.getDeadline());
            project.setDescription(projectCommand.getDescription());
            project.setPriority(projectCommand.getPriority());


            project.setUser(user);
            user.getProjects().add(project);
            userRepository.save(user);
        }

        return "redirect:/projects";
    }
}
