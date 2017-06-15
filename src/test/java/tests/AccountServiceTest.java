package tests;

import com.management.entities.Account.Account;
import com.management.services.Account.AccountService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Dasha on 14.06.2017.
 */
public class AccountServiceTest extends  AbstractTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void getAll() {
        List<Account> list = accountService.getAll();
        Assert.assertEquals(6, list.size());
    }

    @Test
    public void testGet() {

        Integer id = 1;

        Account entity = accountService.get(id);

        Assert.assertEquals("failure - expected id attribute match", id,
                entity.getId());
    }

    @Test
    public void testGetNotFound() {

        Integer id = Integer.MAX_VALUE;

        Account entity = accountService.get(id);

        Assert.assertNull("failure - expected null", entity);
    }

    @Test
    public void testCreate() {

        Account entity = new Account();
        entity.setLogin("name");
        entity.setPassword("pass");

        Account createdEntity = accountService.create(entity);

        Assert.assertNotNull("failure - expected not null", createdEntity);
        Assert.assertNotNull("failure - expected id attribute not null",
                createdEntity.getId());
        Assert.assertEquals("failure - expected text attribute match", "name",
                createdEntity.getLogin());

        List<Account> list = accountService.getAll();

        Assert.assertEquals("failure - expected size", 7, list.size());

    }

    @Test
    public void testUpdate() {

        Integer id = 1;

        Account entity = accountService.get(id);

        Assert.assertNotNull("failure - expected not null", entity);

        String updatedText = entity.getLogin() + " test";
        entity.setLogin(updatedText);
        Account updatedEntity = accountService.update(entity);

        Assert.assertNotNull("failure - expected not null", updatedEntity);
        Assert.assertEquals("failure - expected id attribute match", id,
                updatedEntity.getId());
        Assert.assertEquals("failure - expected text attribute match",
                updatedText, updatedEntity.getLogin());
    }

    @Test
    public void testUpdateNotFound() {

        Exception exception = null;

        Account entity = new Account();
        entity.setId(Integer.MAX_VALUE);
        entity.setLogin("test");
        entity.setPassword("pass");

        try {
            accountService.update(entity);
        } catch (NoResultException e) {
            exception = e;
        }

        Assert.assertNotNull("failure - expected exception", exception);
        Assert.assertTrue("failure - expected NoResultException",
                exception instanceof NoResultException);
    }

    @Test
    public void testDelete() {

        Integer id = 15;

        Account entity = accountService.get(id);

        accountService.delete(id);

        Account deletedEntity = accountService.get(id);

        Assert.assertNull("failure - expected null", deletedEntity);
    }
}
