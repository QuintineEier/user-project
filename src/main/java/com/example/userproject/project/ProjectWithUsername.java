package com.example.userproject.project;

public class ProjectWithUsername {

    private Long id;
    private String projectName;
    private String description;
    private int priority;
    private String username;

    public ProjectWithUsername(Long id,
                               String projectName,
                               String description,
                               int priority,
                               String username) {
        this.id = id;
        this.projectName = projectName;
        this.description = description;
        this.priority = priority;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
