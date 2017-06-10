package com.globalVisionSystem.tests;

import com.globalVisionSystem.domains.manager.Manager;
import com.globalVisionSystem.services.manager.ManagerService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Dasha on 09.06.2017.
 */
@Transactional
public class ManagerServiceTest extends AbstractTest{
    @Autowired
    private ManagerService service;

    @Before
    public void setUp(){
        service.evictCache();
    }

    @After
    public void tearDown(){}

    @Test
    public void testGetAll(){
        List<Manager> list = service.getAll();

        Assert.assertNotNull("failure - expected not null", list);
        Assert.assertNotEquals("failure - expected size", 2, list.size());
    }

    @Test
    public void testGet() {

        Long id = new Long(1);

        Manager entity = service.get(id);

        Assert.assertNotNull("failure - expected not null", entity);
        Assert.assertEquals("failure - expected id attribute match", id,
                entity.getId());

    }

    @Test
    public void testGetNotFound() {

        Long id = Long.MAX_VALUE;

        Manager entity = service.get(id);

        Assert.assertNull("failure - expected null", entity);

    }

    @Test
    public void testCreate() {

        Manager entity = new Manager("name", "lastname", Long.MAX_VALUE);

        Manager createdEntity = service.create(entity);

        Assert.assertNotNull("failure - expected not null", createdEntity);
        Assert.assertNotNull("failure - expected id attribute not null",
                createdEntity.getId());
        Assert.assertEquals("failure - expected text attribute match", "name",
                createdEntity.getFirstName());

        List<Manager> list = service.getAll();

        Assert.assertEquals("failure - expected size", 3, list.size());

    }

    @Test
    public void testCreateWithId() {

        Exception exception = null;

        Manager entity = new Manager("name", "lastname", Long.MAX_VALUE);
        entity.setId(Long.MAX_VALUE);
        entity.setFirstName("test");
        entity.setLastName("lastname");
        entity.setPhone(Long.MAX_VALUE);

        try {
            service.create(entity);
        } catch (EntityExistsException e) {
            exception = e;
        }

        Assert.assertNotNull("failure - expected exception", exception);
        Assert.assertTrue("failure - expected EntityExistsException",
                exception instanceof EntityExistsException);

    }

    @Test
    public void testUpdate() {

        Long id = new Long(1);

        Manager entity = service.get(id);

        Assert.assertNotNull("failure - expected not null", entity);

        String updatedText = entity.getFirstName() + " test";
        entity.setFirstName(updatedText);
        Manager updatedEntity = service.update(entity);

        Assert.assertNotNull("failure - expected not null", updatedEntity);
        Assert.assertEquals("failure - expected id attribute match", id,
                updatedEntity.getId());
        Assert.assertEquals("failure - expected text attribute match",
                updatedText, updatedEntity.getFirstName());

    }

    @Test
    public void testUpdateNotFound() {

        Exception exception = null;

        Manager entity = new Manager("name", "lstname", Long.MAX_VALUE);
        entity.setId(Long.MAX_VALUE);
        entity.setFirstName("test");
        entity.setLastName("lastname");
        entity.setPhone(Long.MAX_VALUE);

        try {
            service.update(entity);
        } catch (NoResultException e) {
            exception = e;
        }

        Assert.assertNotNull("failure - expected exception", exception);
        Assert.assertTrue("failure - expected NoResultException",
                exception instanceof NoResultException);

    }

    @Test
    public void testDelete() {

        Long id = new Long(1);

        Manager entity = service.get(id);

        Assert.assertNotNull("failure - expected not null", entity);

        service.delete(id);

        List<Manager> list = service.getAll();

        Assert.assertEquals("failure - expected size", 1, list.size());

        Manager deletedEntity = service.get(id);

        Assert.assertNull("failure - expected null", deletedEntity);

    }
}
