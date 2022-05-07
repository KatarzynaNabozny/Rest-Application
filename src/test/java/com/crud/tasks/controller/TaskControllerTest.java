package com.crud.tasks.controller;


import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {


    @InjectMocks
    TaskController taskController;

    @Mock
    private  DbService service;

    @Mock
    private TaskMapper taskMapper;

    @Test
    void getTasks() {
        //Given
        TaskController taskController = new TaskController(service,taskMapper);
        //When
         ResponseEntity<List<TaskDto>>listResponseEntity=taskController.getTasks();
         //Then
        assertEquals(HttpStatus.OK,listResponseEntity.getStatusCode());
    }

    @Test
    void deleteTask() throws TaskNotFoundException {
        //Given
        TaskController taskController = new TaskController(service,taskMapper);
        //When
        ResponseEntity<Void>responseEntity = taskController.deleteTask(1L);
        //Then
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    void createTask() {
        //Given
        TaskController taskController = new TaskController(service,taskMapper);
        TaskDto taskDto =new TaskDto(1L,"fef","fe");
        //When
        ResponseEntity<Void>responseEntity = taskController.createTask(taskDto);
        //Then
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    void getTask() throws TaskNotFoundException{
        //Given
        TaskController taskController = new TaskController(service,taskMapper);
        //When
        ResponseEntity<TaskDto>responseEntity = taskController.getTask(1L);
        //Then
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());

    }

    @Test
    void updateTask() {
        //Given
        TaskController taskController = new TaskController(service,taskMapper);
        TaskDto taskDto =new TaskDto(1L,"fef","fe");
        //When
        ResponseEntity<TaskDto>responseEntity = taskController.updateTask(taskDto);
        //Then
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
}
