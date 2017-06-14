package com.globalVisionSystem.services.account;

import com.globalVisionSystem.domains.account.Account;
import com.globalVisionSystem.repository.IRepository;
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
public class AccountService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IRepository<Account, Long> accountRepository;

//    public AccountService(IRepository<Account, Long> accountRepository){
//        this.accountRepository = accountRepository;
//    }

    public List<Account> getAll(){
        logger.info("> findAll");

        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);

        logger.info("< findAll");
        return accounts;
    }

    @Cacheable(
            value = "accounts",
            key = "#id")
    public Account get(Long id){
        logger.info("> findOne id:{}", id);
        Account account = accountRepository.findOne(id);

        logger.info("< findOne id:{}", id);
        return account;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "accounts",
            key = "#result.id")
    public Account create(Account account){
        logger.info("> create");

        // Ensure the entity object to be created does NOT exist in the
        // repository. Prevent the default behavior of save() which will update
        // an existing entity if the entity matching the supplied id exists.
        if (account.getId() != null){
            // Cannot create Account with specified ID value
            logger.error(
                    "Attempted to create a Greeting, but id attribute was not null.");
            throw new EntityExistsException(
                    "The id attribute must be null to persist a new entity.");
        }
        Account savedAccount = accountRepository.save(account);
        logger.info("< create");
        return savedAccount;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "accounts",
            key = "#greeting.id")
    public Account update(Account account){
        logger.info("> update id:{}", account.getId());

        // Ensure the entity object to be updated exists in the repository to
        // prevent the default behavior of save() which will persist a new
        // entity if the entity matching the id does not exist
        Account accountToUpdate = get(account.getId());
        if (accountRepository.findOne(account.getId()) == null){
            // Cannot update Account that hasn't been persisted
            logger.error(
                    "Attempted to update a Greeting, but the entity does not exist.");
            throw new NoResultException("Requested entity not found.");
        }
        accountToUpdate.setLogin(account.getLogin());
        accountToUpdate.setPassword(account.getPassword());

        Account updatedAccount = accountRepository.save(accountToUpdate);

        logger.info("< update id:{}", account.getId());
        return updatedAccount;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CacheEvict(
            value = "accounts",
            key = "#id")
    public void delete(Long id){
        logger.info("> delete id:{}", id);

        accountRepository.delete(id);

        logger.info("< delete id:{}", id);
    }

    @CacheEvict(
            value = "accounts",
            allEntries = true)
    public void evictCache() {
        logger.info("> evictCache");
        logger.info("< evictCache");
    }
}
