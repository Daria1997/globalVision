package com.management.entities.Manager;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.management.entities.Account.Account;
import com.management.entities.Project.Project;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "manager")
public class Manager {
    @Id
    @GeneratedValue
    @Column
    private Integer id;
    @Column(name = "manager_firstName")
    private String firstName;
    @Column(name = "manager_lastName")
    private String lastName;
    @Column(name = "manager_phone")
    private Long phone;
    @JsonIgnore
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Project> projects;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_idAccount")
    private Account account;

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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
