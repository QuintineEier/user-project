package com.example.userproject.project;

import com.example.userproject.user.User;
import com.example.userproject.user.UserRepository;
import com.example.userproject.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
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
            model.addAttribute("userId", option.get().getUser().getId());
            model.addAttribute("projectId", projectId);
            model.addAttribute("daysLeft", projectService.getDaysTillDeadline(option));
            return "specificProject";
        } else {
            return "redirect:/projects";
        }
    }

    @PostMapping
    public String postIndex(@ModelAttribute AddProjectCommand projectCommand) {
        Optional<User> optionalUser = userRepository.findById(projectCommand.getUserId());
        projectService.addProject(projectCommand, optionalUser);
        return "redirect:/projects";
    }

    @PostMapping("/updateProject")
    public String updateProject(@ModelAttribute Project passedProject) {
        Optional<Project> optionalProject = projectRepository.findById(passedProject.getId());
        projectService.updateIfPresent(passedProject, optionalProject);
        return String.format("redirect:/projects/%s", passedProject.getId());
    }

    @GetMapping("/removeProject")
    public String removeProject(@RequestParam Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        projectService.removeIfPresent(id, optionalProject);
        return "redirect:/projects";
    }

    @GetMapping("/setFinished")
    public String setFinished(@RequestParam Long id) {
        Optional<Project> optionalProject = projectService.setFinishedIfPresent(id);
        return String.format("redirect:/projects/%s", optionalProject.get().getId());
    }


}
