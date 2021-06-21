package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional(propagation = Propagation.REQUIRES_NEW)
@SpringBootTest
class DbServiceTestSuite {

    @Autowired
    private DbService dbService;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testGetAllEmpty(){
        //Given
        //When
        List<Task> tasks = dbService.getAllTasks();
        //Then
        assertEquals(0,tasks.size());
    }

    @Test
    public void testGetAll(){
        //Given
        Task task1 = new Task(1L, "test", "test", "test");
        Task task2 = new Task(2L, "test", "test", "test");
        //When
        dbService.saveTask(task1);
        dbService.saveTask(task2);
        List<Task> tasks = dbService.getAllTasks();
        //Then
        assertEquals(2,tasks.size());
        //Cleanup
        try {
            taskRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testSave() {
        //Given
        Task task = new Task(1L, "test", "test", "test");
        //When
        dbService.saveTask(task);
        //Then
        assertEquals(1, taskRepository.count());
        //Cleanup
        try {
            taskRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testGet() throws TaskNotFoundException{
        //Given
        Task task = new Task();
        //When
        Task output = dbService.saveTask(task);
        Long id = output.getId();
        Task output2 = dbService.getTask(id).orElseThrow(TaskNotFoundException::new);
        //Then
        assertEquals(task.getTitle(), output2.getTitle());
        //Cleanup
        try {
            taskRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testDelete() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "test", "test", "test");
        //When
        Task output = dbService.saveTask(task);
        dbService.deleteTask(output.getId());
        //Then
        assertEquals(0, taskRepository.count());
        //Cleanup
        try {
            taskRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }
}