package com.example.userproject.project;

import com.example.userproject.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getProjects(){
        return (List<Project>) projectRepository.findAll();
    }

    public List<ProjectWithUsername> getProjectsWithUsername() {
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
                .map( (Project p) -> {
                    return new ProjectWithUsername(p.getId(),
                            p.getProjectName(),
                            p.getDescription(),
                            p.getPriority(),
                            p.getUser() != null ? p.getUser().getName() : "Nameless");
                })
                .collect(Collectors.toList());
    }

    public void addNewProject(Project project) {
        Optional<Project> projectOptional = projectRepository
                .findByProjectName(project.getProjectName());
        if (projectOptional.isPresent()) {
            throw new IllegalStateException("Projectname is already taken!");
        }
        projectRepository.save(project);
    }

    public void deleteProject(Long projectId) {
        boolean exists = projectRepository.existsById(projectId);
        if (!exists) {
            throw new IllegalStateException("Project with id " + projectId + " does not exists");
        }
        projectRepository.deleteById(projectId);
    }
}
