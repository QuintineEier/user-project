package com.example.userproject.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "json/project")
public class ProjectJsonController {

    private final ProjectService projectService;

    @Autowired
    public ProjectJsonController(ProjectService projectService){
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectWithUsername> getProjects() {
        return projectService.getProjectsWithUsername();
    }

    @PostMapping
    public void registerNewProject(@RequestBody Project project){
        projectService.addNewProject(project);
    }

    @DeleteMapping(path = "{projectId}")
    public void deleteProject(@PathVariable("projectId") Long projectId){
        projectService.deleteProject(projectId);
    }

}
