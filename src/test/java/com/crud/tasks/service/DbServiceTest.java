package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {
@InjectMocks
DbService dbService;
@Mock
TaskRepository taskRepository;

    @Test
    void getAllTasks() {
        //given
        Task task = new Task();
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        //when
        List<Task> allTasks = dbService.getAllTasks();
        //then
        assertEquals(0, allTasks.size());

    }

    @Test
    void getTask() {
    }

    @Test
    void saveTask() {
    }

    @Test
    void deleteTask() {
    }
}