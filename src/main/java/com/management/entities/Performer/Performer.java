package com.management.entities.Performer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.management.entities.Account.Account;
import com.management.entities.Project.Project;
import com.management.entities.Task.Task;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "performer")
public class Performer {
    @Id
    @GeneratedValue
    @Column
    private Integer id;
    @Column(name = "peformer_firstName")
    private String firstName;
    @Column(name = "performer_lastName")
    private String lastName;
    @Column(name = "performer_phone")
    private Long phone;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_idAccount")
    private Account account;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @JsonIgnore
    @OneToMany(mappedBy = "performer", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
