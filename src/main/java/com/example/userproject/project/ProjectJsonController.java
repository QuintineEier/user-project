package com.example.userproject.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "json/project")
public class ProjectJsonController {

    private final ProjectService projectService;

    @Autowired
    public ProjectJsonController(ProjectService projectService){
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getProjects(){
        return projectService.getProjects();
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
