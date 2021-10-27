package com.example.userproject.project;

import com.example.userproject.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "project", schema = "public")
@JsonIgnoreProperties({"user"})
public class Project {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String projectName;

    @Column()
    private LocalDate deadline;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int priority;

    @Column
    private boolean finished;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Project() {

    }

    public Project(Long id,
                   String projectName,
                   LocalDate deadline,
                   String description,
                   boolean finished,
                   int priority) {
        this.id = id;
        this.projectName = projectName;
        this.deadline = deadline;
        this.description = description;
        this.priority = priority;
        this.finished = finished;
    }

    public Project(String projectName,
                   LocalDate deadline,
                   String description,
                   boolean finished,
                   int priority) {
        this.projectName = projectName;
        this.deadline = deadline;
        this.description = description;
        this.priority = priority;
        this.finished = finished;
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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
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

    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", deadline=" + deadline +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", finished=" + finished +
                ", user=" + user +
                '}';
    }
}
