package com.globalVisionSystem.domains.task;

import com.globalVisionSystem.domains.base.BaseEntity;
import com.globalVisionSystem.domains.manager.Manager;
import com.globalVisionSystem.domains.performer.Performer;
import com.globalVisionSystem.domains.project.Project;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by Dasha on 06.06.2017.
 */

@Entity
public class Task extends BaseEntity{
    private String name;
    private String description;
    private Date date_of_beginning;
    private Date date_of_ending;
    private State state;
    @ManyToOne
    private Project project;
    @ManyToOne
    private Performer performer;

    protected Task(){
        super();
    }

    public Task(String name, String description, Date date_of_beginning, Date date_of_ending, State state) {
        this();
        this.name = name;
        this.description = description;
        this.date_of_beginning = date_of_beginning;
        this.date_of_ending = date_of_ending;
        this.state = state;
    }

    public Performer getPerformer() {
        return performer;
    }

    public void setPerformer(Performer performer) {
        this.performer = performer;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_of_beginning() {
        return date_of_beginning;
    }

    public void setDate_of_beginning(Date date_of_beginning) {
        this.date_of_beginning = date_of_beginning;
    }

    public Date getDate_of_ending() {
        return date_of_ending;
    }

    public void setDate_of_ending(Date date_of_ending) {
        this.date_of_ending = date_of_ending;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date_of_beginning=" + date_of_beginning +
                ", date_of_ending=" + date_of_ending +
                ", state=" + state +
                ", project=" + project +
                ", performer=" + performer +
                '}';
    }
}
