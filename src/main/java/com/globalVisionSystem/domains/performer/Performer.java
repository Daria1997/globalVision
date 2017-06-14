package com.globalVisionSystem.domains.performer;

import com.globalVisionSystem.domains.account.Account;
import com.globalVisionSystem.domains.base.BaseEntity;
import com.globalVisionSystem.domains.project.Project;
import com.globalVisionSystem.domains.task.Task;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dasha on 07.06.2017.
 */
@Entity
@Table(name = "performer")
public class Performer {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private Long phone;
    @ManyToOne
    private Account account;
    @ManyToOne
    private Project project;
    @OneToMany(mappedBy = "performer", cascade = CascadeType.ALL)
    private List<Task> tasks;

    protected Performer(){
        super();
        tasks = new ArrayList<>();
    }

    public Performer(String firstName, String lastName, Long phone) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task){
        task.setPerformer(this);
        tasks.add(task);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Performer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone=" + phone +
                ", account=" + account +
                ", project=" + project +
                ", tasks=" + tasks +
                '}';
    }
}
