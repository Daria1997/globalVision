package com.management.services.Task;

import com.management.entities.Project.Project;
import com.management.entities.Task.Task;
import com.management.repositories.Project.ProjectRepository;
import com.management.repositories.Task.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dasha on 14.06.2017.
 */
@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class TaskService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskRepository taskRepository;

    public TaskService (TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> getAll(){
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @Cacheable(
            value = "tasks",
            key = "#id")
    public Task get(Integer id){
        return taskRepository.findOne(id);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "tasks",
            key = "#result.id")
    public Task create(Task task){
        // Ensure the entity object to be created does NOT exist in the
        // repository. Prevent the default behavior of save() which will update
        // an existing entity if the entity matching the supplied id exists.
        if (task.getId() != null){
            // Cannot create Task with specified ID value
            logger.error(
                    "Attempted to create a Task, but id attribute was not null.");
            throw new EntityExistsException(
                    "The id attribute must be null to persist a new entity.");
        }
        return taskRepository.save(task);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "tasks",
            key = "#task.id")
    public Task update(Task task){
        // Ensure the entity object to be updated exists in the repository to
        // prevent the default behavior of save() which will persist a new
        // entity if the entity matching the id does not exist
        Task taskToUpdate = get(task.getId());
        if (taskRepository.findOne(task.getId()) == null){
            // Cannot update Task that hasn't been persisted
            logger.error(
                    "Attempted to update a Task, but the entity does not exist.");
            throw new NoResultException("Requested entity not found.");
        }
        taskToUpdate.setName(task.getName());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setDate_of_beginning(task.getDate_of_beginning());
        taskToUpdate.setDate_of_ending(task.getDate_of_ending());
        taskToUpdate.setPerformer(task.getPerformer());
        taskToUpdate.setProject(task.getProject());
        taskToUpdate.setState(task.getState());

        return taskRepository.save(taskToUpdate);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CacheEvict(
            value = "tasks",
            key = "#id")
    public void delete(Integer id){
        taskRepository.delete(id);
    }
}
