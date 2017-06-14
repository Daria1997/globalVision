package com.management.rest;

import com.management.entities.Account.Account;
import com.management.entities.Manager.Manager;
import com.management.entities.Performer.Performer;
import com.management.entities.Project.Project;
import com.management.entities.Task.Task;
import com.management.services.Account.AccountService;
import com.management.services.Manager.ManagerService;
import com.management.services.Performer.PerformerService;
import com.management.services.Project.ProjectService;
import com.management.services.Task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest")
public class Rest {
    private AccountService accountService;
    private ManagerService managerService;
    private PerformerService performerService;
    private ProjectService projectService;
    private TaskService taskService;

    public Rest() {}

    @Autowired
    public Rest(AccountService accountService, ManagerService managerService, PerformerService performerService, ProjectService projectService, TaskService taskService) {
        this.accountService = accountService;
        this.managerService = managerService;
        this.performerService = performerService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("account")
    public Account getAccount(Integer id) {
        return accountService.get(id);
}

    @GetMapping("accounts")
    public List<Account> getAccounts() { return accountService.getAll(); }

    @GetMapping("manager")
    public Manager getManager(Integer id) {
        return managerService.get(id);
    }

    @GetMapping("managers")
    public List<Manager> getManagers() { return managerService.getAll(); }

    @GetMapping("performer")
    public Performer getPerformer(Integer id) {
        return performerService.get(id);
    }

    @GetMapping("performers")
    public List<Performer> getPerformers() { return performerService.getAll(); }

    @GetMapping("project")
    public Project getProject(Integer id) {
        return projectService.get(id);
    }

    @GetMapping("projects")
    public List<Project> getProjects() { return projectService.getAll(); }

    @GetMapping("task")
    public Task getTask(Integer id) {
        return taskService.get(id);
    }

    @GetMapping("tasks")
    public List<Task> getTasks() { return taskService.getAll(); }

    @GetMapping("createAccount")
    public Account createAccount(){
        Account account = new Account();
        account.setId(10);
        account.setLogin("fhghf");
        account.setPassword("gfhd");
        accountService.create(account);
        return accountService.get(10);
    }
}
