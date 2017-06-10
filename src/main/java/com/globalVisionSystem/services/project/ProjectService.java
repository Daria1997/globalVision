package com.globalVisionSystem.services.project;

import com.globalVisionSystem.repository.IRepository;
import com.globalVisionSystem.domains.project.Project;
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
public class ProjectService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IRepository<Project, Long> projectRepository;

    public List<Project> getAll(){
        logger.info("> findAll");

        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);

        logger.info("< findAll");
        return projects;
    }

    @Cacheable(
            value = "projects",
            key = "#id")
    public Project get(Long id){
        logger.info("> findOne id:{}", id);
        Project project = projectRepository.findOne(id);

        logger.info("< findOne id:{}", id);
        return project;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "projects",
            key = "#result.id")
    public Project create(Project project){
        logger.info("> create");

        // Ensure the entity object to be created does NOT exist in the
        // repository. Prevent the default behavior of save() which will update
        // an existing entity if the entity matching the supplied id exists.
        if (project.getId() != null){
            // Cannot create Project with specified ID value
            logger.error(
                    "Attempted to create a Greeting, but id attribute was not null.");
            throw new EntityExistsException(
                    "The id attribute must be null to persist a new entity.");
        }
        Project savedProject = projectRepository.save(project);
        logger.info("< create");
        return savedProject;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "projects",
            key = "#greeting.id")
    public Project update(Project project){
        logger.info("> update id:{}", project.getId());

        // Ensure the entity object to be updated exists in the repository to
        // prevent the default behavior of save() which will persist a new
        // entity if the entity matching the id does not exist
        Project projectToUpdate = get(project.getId());
        if (projectRepository.findOne(project.getId()) == null){
            // Cannot update Project that hasn't been persisted
            logger.error(
                    "Attempted to update a Greeting, but the entity does not exist.");
            throw new NoResultException("Requested entity not found.");
        }
        projectToUpdate.setName(project.getName());
        projectToUpdate.setManager(project.getManager());
        Project updatedProject = projectRepository.save(projectToUpdate);

        logger.info("< update id:{}", project.getId());
        return updatedProject;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CacheEvict(
            value = "projects",
            key = "#id")
    public void delete(Long id){
        logger.info("> delete id:{}", id);

        projectRepository.delete(id);

        logger.info("< delete id:{}", id);
    }

    @CacheEvict(
            value = "projects",
            allEntries = true)
    public void evictCache() {
        logger.info("> evictCache");
        logger.info("< evictCache");
    }
}
