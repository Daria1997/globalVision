package com.globalVisionSystem.domains.project;

import com.globalVisionSystem.domains.base.BaseEntity;
import com.globalVisionSystem.domains.manager.Manager;
import com.globalVisionSystem.domains.performer.Performer;
import com.globalVisionSystem.domains.task.Task;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dasha on 06.06.2017.
 */

@Entity
public class Project extends BaseEntity {
    private String name;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Performer> performers;
    @ManyToOne
    private Manager manager;

    protected Project(){
        super();
        tasks = new ArrayList<>();
        performers = new ArrayList<>();
    }

    public Project (String name) {
        this();
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task){
        task.setProject(this);
        tasks.add(task);
    }

    public List<Performer> getPerformers() {
        return performers;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", tasks=" + tasks +
                ", performers=" + performers +
                ", manager=" + manager +
                '}';
    }
}
