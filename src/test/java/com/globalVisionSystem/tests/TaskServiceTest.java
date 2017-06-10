package com.globalVisionSystem.tests;

import com.globalVisionSystem.domains.task.State;
import com.globalVisionSystem.domains.task.Task;
import com.globalVisionSystem.services.task.TaskService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

/**
 * Created by Dasha on 09.06.2017.
 */
@Transactional
public class TaskServiceTest extends AbstractTest{
    @Autowired
    private TaskService service;

    @Before
    public void setUp(){
        service.evictCache();
    }

    @After
    public void tearDown(){}

    @Test
    public void testGetAll(){
        List<Task> list = service.getAll();

        Assert.assertNotNull("failure - expected not null", list);
        Assert.assertNotEquals("failure - expected size", 2, list.size());
    }

    @Test
    public void testGet() {

        Long id = new Long(1);

        Task entity = service.get(id);

        Assert.assertNotNull("failure - expected not null", entity);
        Assert.assertEquals("failure - expected id attribute match", id,
                entity.getId());

    }

    @Test
    public void testGetNotFound() {

        Long id = Long.MAX_VALUE;

        Task entity = service.get(id);

        Assert.assertNull("failure - expected null", entity);

    }

    @Test
    public void testCreate() {

        Date date = new Date();
        Task entity = new Task("name", "description", date, date, State.IS_NOT_STARTED);

        Task createdEntity = service.create(entity);

        Assert.assertNotNull("failure - expected not null", createdEntity);
        Assert.assertNotNull("failure - expected id attribute not null",
                createdEntity.getId());
        Assert.assertEquals("failure - expected text attribute match", "name",
                createdEntity.getName());

        List<Task> list = service.getAll();

        Assert.assertEquals("failure - expected size", 3, list.size());

    }

    @Test
    public void testCreateWithId() {

        Exception exception = null;

        Date date = new Date();

        Task entity = new Task("name", "description", date, date, State.IS_NOT_STARTED);
        entity.setId(Long.MAX_VALUE);
        entity.setName("test");
        entity.setDescription("task");
        entity.setDate_of_beginning(date);
        entity.setDate_of_ending(date);
        entity.setState(State.IS_NOT_STARTED);

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

        Task entity = service.get(id);

        Assert.assertNotNull("failure - expected not null", entity);

        String updatedText = entity.getName() + " test";
        entity.setName(updatedText);
        Task updatedEntity = service.update(entity);

        Assert.assertNotNull("failure - expected not null", updatedEntity);
        Assert.assertEquals("failure - expected id attribute match", id,
                updatedEntity.getId());
        Assert.assertEquals("failure - expected text attribute match",
                updatedText, updatedEntity.getName());

    }

    @Test
    public void testUpdateNotFound() {

        Exception exception = null;

        Date date = new Date();

        Task entity = new Task("name", "description", date, date, State.IS_NOT_STARTED);
        entity.setId(Long.MAX_VALUE);
        entity.setName("test");
        entity.setDescription("task");
        entity.setDate_of_beginning(date);
        entity.setDate_of_ending(date);
        entity.setState(State.IS_NOT_STARTED);

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

        Task entity = service.get(id);

        Assert.assertNotNull("failure - expected not null", entity);

        service.delete(id);

        List<Task> list = service.getAll();

        Assert.assertEquals("failure - expected size", 1, list.size());

        Task deletedEntity = service.get(id);

        Assert.assertNull("failure - expected null", deletedEntity);

    }
}
