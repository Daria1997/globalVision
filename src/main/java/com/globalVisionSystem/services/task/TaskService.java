package com.globalVisionSystem.services.task;

import com.globalVisionSystem.repository.IRepository;
import com.globalVisionSystem.domains.task.Task;
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
 * Created by Dasha on 07.06.2017.
 */
@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class TaskService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IRepository<Task, Long> taskRepository;

    public List<Task> getAll(){
        logger.info("> findAll");

        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);

        logger.info("< findAll");
        return tasks;
    }

    @Cacheable(
            value = "tasks",
            key = "#id")
    public Task get(Long id){
        logger.info("> findOne id:{}", id);
        Task task = taskRepository.findOne(id);

        logger.info("< findOne id:{}", id);
        return task;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "tasks",
            key = "#result.id")
    public Task create(Task task){
        logger.info("> create");

        // Ensure the entity object to be created does NOT exist in the
        // repository. Prevent the default behavior of save() which will update
        // an existing entity if the entity matching the supplied id exists.
        if (task.getId() != null){
            // Cannot create Task with specified ID value
            logger.error(
                    "Attempted to create a Greeting, but id attribute was not null.");
            throw new EntityExistsException(
                    "The id attribute must be null to persist a new entity.");
        }
        Task savedTask = taskRepository.save(task);
        logger.info("< create");
        return savedTask;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "tasks",
            key = "#greeting.id")
    public Task update(Task task){
        logger.info("> update id:{}", task.getId());

        // Ensure the entity object to be updated exists in the repository to
        // prevent the default behavior of save() which will persist a new
        // entity if the entity matching the id does not exist
        Task taskToUpdate = get(task.getId());
        if (taskRepository.findOne(task.getId()) == null){
            // Cannot update Task that hasn't been persisted
            logger.error(
                    "Attempted to update a Greeting, but the entity does not exist.");
            throw new NoResultException("Requested entity not found.");
        }
        taskToUpdate.setName(task.getName());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setDate_of_beginning(task.getDate_of_beginning());
        taskToUpdate.setDate_of_ending(task.getDate_of_ending());
        taskToUpdate.setPerformer(task.getPerformer());
        taskToUpdate.setProject(task.getProject());
        taskToUpdate.setState(task.getState());

        Task updatedTask = taskRepository.save(taskToUpdate);

        logger.info("< update id:{}", task.getId());
        return updatedTask;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CacheEvict(
            value = "tasks",
            key = "#id")
    public void delete(Long id){
        logger.info("> delete id:{}", id);

        taskRepository.delete(id);

        logger.info("< delete id:{}", id);
    }

    @CacheEvict(
            value = "tasks",
            allEntries = true)
    public void evictCache() {
        logger.info("> evictCache");
        logger.info("< evictCache");
    }
}
