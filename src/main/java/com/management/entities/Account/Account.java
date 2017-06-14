package com.management.entities.Account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.management.entities.Manager.Manager;
import com.management.entities.Performer.Performer;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAccount")
    private Integer id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Performer> performers;
    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Manager> managers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public List<Performer> getPerformers() {
        return performers;
    }

    public void setPerformers(List<Performer> performers) {
        this.performers = performers;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }
}
