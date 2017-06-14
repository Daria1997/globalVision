package com.management.services.Account;

import com.management.entities.Account.Account;
import com.management.repositories.Account.AccountRepository;
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
public class AccountService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountRepository accountRepository;

    public AccountService(){}

    public AccountService (AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public List<Account> getAll(){
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }

    @Cacheable(
            value = "accounts",
            key = "#id")
    public Account get(Integer id){
        return accountRepository.findOne(id);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "accounts",
            key = "#result.id")
    public Account create(Account account){
        // Ensure the entity object to be created does NOT exist in the
        // repository. Prevent the default behavior of save() which will update
        // an existing entity if the entity matching the supplied id exists.
//        if (account.getId() != null){
//            // Cannot create Account with specified ID value
//            logger.error(
//                    "Attempted to create an Account, but id attribute was not null.");
//            throw new EntityExistsException(
//                    "The id attribute must be null to persist a new entity.");
//        }
        return accountRepository.save(account);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "accounts",
            key = "#account.id")
    public Account update(Account account){
        // Ensure the entity object to be updated exists in the repository to
        // prevent the default behavior of save() which will persist a new
        // entity if the entity matching the id does not exist
        Account accountToUpdate = get(account.getId());
        if (accountRepository.findOne(account.getId()) == null){
            // Cannot update Account that hasn't been persisted
            logger.error(
                    "Attempted to update an Account, but the entity does not exist.");
            throw new NoResultException("Requested entity not found.");
        }
        accountToUpdate.setLogin(account.getLogin());
        accountToUpdate.setPassword(account.getPassword());
        return accountRepository.save(accountToUpdate);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CacheEvict(
            value = "accounts",
            key = "#id")
    public void delete(Integer id){
        accountRepository.delete(id);
    }
}
