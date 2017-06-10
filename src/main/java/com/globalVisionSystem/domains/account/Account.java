package com.globalVisionSystem.domains.account;

import com.globalVisionSystem.domains.base.BaseEntity;
import com.globalVisionSystem.domains.manager.Manager;
import com.globalVisionSystem.domains.performer.Performer;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dasha on 06.06.2017.
 */

@Entity
public class Account extends BaseEntity{
    private String login;
    private String password;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Performer> performers;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Manager> managers;

    protected Account(){
        super();
        performers = new ArrayList<>();
        managers = new ArrayList<>();
    }

    public Account(String login, String password) {
        this();
        this.login = login;
        this.password = password;
    }

    public List<Performer> getPerformers() {
        return performers;
    }

    public void addPerformer(Performer performer){
        performer.setAccount(this);
        performers.add(performer);
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void addManager(Manager manager){
        manager.setAccount(this);
        managers.add(manager);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", performers=" + performers +
                ", managers=" + managers +
                '}';
    }
}
