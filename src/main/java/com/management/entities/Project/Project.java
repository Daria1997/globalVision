package com.management.entities.Project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.management.entities.Manager.Manager;
import com.management.entities.Performer.Performer;
import com.management.entities.Task.Task;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue
    @Column
    private Integer id;
    @Column(name = "project_name")
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks;
    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Performer> performers;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "managerId")
    private Manager manager;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Performer> getPerformers() {
        return performers;
    }

    public void setPerformers(List<Performer> performers) {
        this.performers = performers;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
