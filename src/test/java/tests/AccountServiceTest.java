package tests;

import com.management.entities.Account.Account;
import com.management.repositories.Account.AccountRepository;
import com.management.services.Account.AccountService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dasha on 14.06.2017.
 */

public class AccountServiceTest {
    @Autowired
    private static AccountService service;

    @BeforeClass
    public static void setUp() {
        service = new AccountService();
    }

    @Test
    public void testGetAll(){
        Account acc = new Account();
        acc.setId(1);
        acc.setLogin("fg");
        acc.setPassword("kgj");
        service.create(acc);
        List<Account> list = service.getAll();

        Assert.assertNotNull("failure - expected not null", list);
        Assert.assertNotEquals("failure - expected size", 3, list.size());
    }

//    @Test
//    public void testGet() {
//
//        Integer id = 1;
//
//        Account entity = service.get(id);
//
//        Assert.assertNotNull("failure - expected not null", entity);
//        Assert.assertEquals("failure - expected id attribute match", id,
//                entity.getId());
//    }
//
//    @Test
//    public void testGetNotFound() {
//
//        Integer id = Integer.MAX_VALUE;
//
//        Account entity = service.get(id);
//
//        Assert.assertNull("failure - expected null", entity);
//    }
//
//    @Test
//    public void testCreate() {
//
//        Account entity = new Account("login", "password");
//
//        Account createdEntity = service.create(entity);
//
//        Assert.assertNotNull("failure - expected not null", createdEntity);
//        Assert.assertNotNull("failure - expected id attribute not null",
//                createdEntity.getId());
//        Assert.assertEquals("failure - expected text attribute match", "name",
//                createdEntity.getLogin());
//
//        List<Account> list = service.getAll();
//
//        Assert.assertEquals("failure - expected size", 5, list.size());
//    }
//
//    @Test
//    public void testCreateWithId() {
//
//        Exception exception = null;
//
//        Account entity = new Account("name", "pass");
//        entity.setId(Integer.MAX_VALUE);
//        entity.setLogin("test");
//        entity.setPassword("pass");
//
//        try {
//            service.create(entity);
//        } catch (EntityExistsException e) {
//            exception = e;
//        }
//
//        Assert.assertNotNull("failure - expected exception", exception);
//        Assert.assertTrue("failure - expected EntityExistsException",
//                exception instanceof EntityExistsException);
//    }
//
//    @Test
//    public void testUpdate() {
//
//        Integer id = 7;
//
//        Account entity = service.get(id);
//
//        Assert.assertNotNull("failure - expected not null", entity);
//
//        String updatedText = entity.getLogin() + " test";
//        entity.setLogin(updatedText);
//        Account updatedEntity = service.update(entity);
//
//        Assert.assertNotNull("failure - expected not null", updatedEntity);
//        Assert.assertEquals("failure - expected id attribute match", id,
//                updatedEntity.getId());
//        Assert.assertEquals("failure - expected text attribute match",
//                updatedText, updatedEntity.getLogin());
//
//    }
//
//    @Test
//    public void testUpdateNotFound() {
//
//        Exception exception = null;
//
//        Account entity = new Account("name", "pass");
//        entity.setId(Integer.MAX_VALUE);
//        entity.setLogin("test");
//        entity.setPassword("pass");
//
//        try {
//            service.update(entity);
//        } catch (NoResultException e) {
//            exception = e;
//        }
//
//        Assert.assertNotNull("failure - expected exception", exception);
//        Assert.assertTrue("failure - expected NoResultException",
//                exception instanceof NoResultException);
//    }
//
//    @Test
//    public void testDelete() {
//
//        Integer id = 8;
//
//        Account entity = service.get(id);
//
//        Assert.assertNotNull("failure - expected not null", entity);
//
//        service.delete(id);
//
//        List<Account> list = service.getAll();
//
//        Assert.assertEquals("failure - expected size", 6, list.size());
//
//        Account deletedEntity = service.get(id);
//
//        Assert.assertNull("failure - expected null", deletedEntity);
//
//    }
}
