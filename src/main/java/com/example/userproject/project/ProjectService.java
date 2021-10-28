package com.example.userproject.project;

import com.example.userproject.user.User;
import com.example.userproject.user.UserRepository;
import com.example.userproject.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final UserRepository userRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
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

    public void removeIfPresent(Long id, Optional<Project> optionalProject) {
        if (optionalProject.isPresent()) {
            projectRepository.deleteById(id);
        }
    }

    public void addProject(AddProjectCommand projectCommand, Optional<User> optionalUser) {
        if (optionalUser.isPresent()) {
            saveIfPresent(projectCommand, optionalUser);
        }
    }

    public void saveIfPresent(AddProjectCommand projectCommand, Optional<User> optionalUser) {
        User user = optionalUser.get();

        Project project = new Project();
        project.setProjectName(projectCommand.getProjectName());
        project.setDeadline(projectCommand.getDeadline());
        project.setDescription(projectCommand.getDescription());
        project.setPriority(projectCommand.getPriority());
        project.setFinished(false);

        project.setUser(user);
        user.getProjects().add(project);
        userRepository.save(user);
    }

    public void updateIfPresent(Project passedProject, Optional<Project> optionalProject) {
        if (optionalProject.isPresent()) {
            Project existingProject = optionalProject.get();
            existingProject.setProjectName(passedProject.getProjectName());
            existingProject.setPriority(passedProject.getPriority());
            existingProject.setDescription(passedProject.getDescription());
            projectRepository.save(existingProject);
        }
    }

    public Optional<Project> setFinishedIfPresent(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project existingProject = optionalProject.get();
            existingProject.setFinished(true);
            projectRepository.save(existingProject);
        }
        return optionalProject;
    }

    public long getDaysTillDeadline(Optional<Project> option) {
        LocalDate deadline = option.get().getDeadline();
        LocalDate today = LocalDate.now();

        return DateUtil.totalDaysBetween(today, deadline);
    }
}
