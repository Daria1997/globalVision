package com.management.services.Manager;

import com.management.entities.Manager.Manager;
import com.management.repositories.Manager.ManagerRepository;
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
public class ManagerService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ManagerRepository managerRepository;

    public ManagerService (ManagerRepository managerRepository){
        this.managerRepository = managerRepository;
    }

    public List<Manager> getAll(){
        List<Manager> managers = new ArrayList<>();
        managerRepository.findAll().forEach(managers::add);
        return managers;
    }

    @Cacheable(
            value = "managers",
            key = "#id")
    public Manager get(Integer id){
        return managerRepository.findOne(id);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "managers",
            key = "#result.id")
    public Manager create(Manager manager){
        // Ensure the entity object to be created does NOT exist in the
        // repository. Prevent the default behavior of save() which will update
        // an existing entity if the entity matching the supplied id exists.
        if (manager.getId() != null){
            // Cannot create Manager with specified ID value
            logger.error(
                    "Attempted to create a Manager, but id attribute was not null.");
            throw new EntityExistsException(
                    "The id attribute must be null to persist a new entity.");
        }
        return managerRepository.save(manager);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "managers",
            key = "#manager.id")
    public Manager update(Manager manager){
        // Ensure the entity object to be updated exists in the repository to
        // prevent the default behavior of save() which will persist a new
        // entity if the entity matching the id does not exist
        Manager managerToUpdate = get(manager.getId());
        if (managerRepository.findOne(manager.getId()) == null){
            // Cannot update Manager that hasn't been persisted
            logger.error(
                    "Attempted to update a Manager, but the entity does not exist.");
            throw new NoResultException("Requested entity not found.");
        }
        managerToUpdate.setFirstName(manager.getFirstName());
        managerToUpdate.setLastName(manager.getLastName());
        managerToUpdate.setPhone(manager.getPhone());
        managerToUpdate.setAccount(manager.getAccount());

        return managerRepository.save(managerToUpdate);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CacheEvict(
            value = "managers",
            key = "#id")
    public void delete(Integer id){
        managerRepository.delete(id);
    }
}
