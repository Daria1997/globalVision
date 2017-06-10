package com.globalVisionSystem.domains.manager;

import com.globalVisionSystem.domains.account.Account;
import com.globalVisionSystem.domains.base.BaseEntity;
import com.globalVisionSystem.domains.performer.Performer;
import com.globalVisionSystem.domains.project.Project;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dasha on 07.06.2017.
 */
public class Manager extends BaseEntity {
    private String firstName;
    private String lastName;
    private Long phone;
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Project> projects;
    @ManyToOne
    private Account account;

    protected Manager(){
        super();
        projects = new ArrayList<>();
    }

    public Manager(String firstName, String lastName, Long phone) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void addPoject(Project project){
        project.setManager(this);
        projects.add(project);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
        return "Manager{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone=" + phone +
                ", projects=" + projects +
                ", account=" + account +
                '}';
    }
}
