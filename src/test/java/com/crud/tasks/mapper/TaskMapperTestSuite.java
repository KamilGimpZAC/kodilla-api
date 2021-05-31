package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTaskDto(){
        //Given
        Task task = new Task();
        task.setTitle("test");
        task.setContent("test");
        task.setListId("test");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getTitle(), taskDto.getTitle());
        assertEquals(task.getContent(), taskDto.getContent());
        assertEquals(task.getListId(), taskDto.getListId());
    }

    @Test
    public void testMapToTask(){
        //Given
        TaskDto taskDto = new TaskDto(1L, "test", "test", "test");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(taskDto.getId(), task.getId());
        assertEquals(taskDto.getTitle(), task.getTitle());
        assertEquals(taskDto.getContent(), task.getContent());
        assertEquals(taskDto.getListId(), task.getListId());
    }

    @Test
    public void testMapToTaskDtoList(){
        //Given
        List<Task> tasks = new ArrayList<>(){{
            add(new Task());
            add(new Task());
        }};
        //When
        List<TaskDto> tasksDto = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertEquals(tasks.size(), tasksDto.size());
    }
}