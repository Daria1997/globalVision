package com.globalVisionSystem.services.performer;

import com.globalVisionSystem.domains.performer.Performer;
import com.globalVisionSystem.repository.PerformerRepository;
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
public class PerformerService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PerformerRepository performerRepository;

    @Autowired
    public PerformerService(PerformerRepository performerRepository) {
        this.performerRepository = performerRepository;
    }
//    private IRepository<Performer, Long> performerRepository;

//    @Autowired
//    public PerformerService(IRepository<Performer, Long> performerRepository){
//        this.performerRepository = performerRepository;
//    }

    public List<Performer> getAll(){
        logger.info("> findAll");

        List<Performer> performers = new ArrayList<>();
        performerRepository.findAll().forEach(performers::add);

        logger.info("< findAll");
        return performers;
    }

    @Cacheable(
            value = "performers",
            key = "#id")
    public Performer get(Long id){
        logger.info("> findOne id:{}", id);
        Performer performer = performerRepository.findOne(id);

        logger.info("< findOne id:{}", id);
        return performer;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "performers",
            key = "#result.id")
    public Performer create(Performer performer){
        logger.info("> create");

        // Ensure the entity object to be created does NOT exist in the
        // repository. Prevent the default behavior of save() which will update
        // an existing entity if the entity matching the supplied id exists.
        if (performer.getId() != null){
            // Cannot create Performer with specified ID value
            logger.error(
                    "Attempted to create a Greeting, but id attribute was not null.");
            throw new EntityExistsException(
                    "The id attribute must be null to persist a new entity.");
        }
        Performer savedPerformer = performerRepository.save(performer);
        logger.info("< create");
        return savedPerformer;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "performers",
            key = "#greeting.id")
    public Performer update(Performer performer){
        logger.info("> update id:{}", performer.getId());

        // Ensure the entity object to be updated exists in the repository to
        // prevent the default behavior of save() which will persist a new
        // entity if the entity matching the id does not exist
        Performer performerToUpdate = get(performer.getId());
        if (performerRepository.findOne(performer.getId()) == null){
            // Cannot update Performer that hasn't been persisted
            logger.error(
                    "Attempted to update a Greeting, but the entity does not exist.");
            throw new NoResultException("Requested entity not found.");
        }
        performerToUpdate.setFirstName(performer.getFirstName());
        performerToUpdate.setLastName(performer.getLastName());
        performerToUpdate.setPhone(performer.getPhone());
        performerToUpdate.setAccount(performer.getAccount());
        performerToUpdate.setProject(performer.getProject());
        Performer updatedPerformer = performerRepository.save(performerToUpdate);

        logger.info("< update id:{}", performer.getId());
        return updatedPerformer;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CacheEvict(
            value = "performers",
            key = "#id")
    public void delete(Long id){
        logger.info("> delete id:{}", id);

        performerRepository.delete(id);

        logger.info("< delete id:{}", id);
    }

    @CacheEvict(
            value = "performers",
            allEntries = true)
    public void evictCache() {
        logger.info("> evictCache");
        logger.info("< evictCache");
    }
}
