package com.management.entities.Task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.management.entities.Performer.Performer;
import com.management.entities.Project.Project;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Dasha on 14.06.2017.
 */
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue
    @Column
    private Integer id;
    @Column(name = "task_name")
    private String name;
    @Column(name = "task_description")
    private String description;
    @Column(name = "task_dateOfBegin")
    @Temporal(TemporalType.DATE)
    private Date date_of_beginning;
    @Column(name = "task_dateOfEnd")
    @Temporal(TemporalType.DATE)
    private Date date_of_ending;
    @Column(name = "task_state")
    private State state;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "performer_id")
    private Performer performer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Performer getPerformer() {
        return performer;
    }

    public void setPerformer(Performer performer) {
        this.performer = performer;
    }
}
