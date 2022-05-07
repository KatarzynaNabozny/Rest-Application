package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class TaskMapperTest {

    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    void mapToTask(){
        //Given
         taskMapper = new TaskMapper();
        TaskDto taskDto = new TaskDto(10L,"fgfk","fyfkl");
        //When
        Task task =taskMapper.mapToTask(taskDto);
        //Then
        assertEquals("fgfk",task.getTitle());

    }

    @Test
    void mapToTaskDto(){
        //Given
        taskMapper = new TaskMapper();
        Task task =new Task();
        //When
        TaskDto taskDto =taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(null,taskDto.getTitle());

    }

    @Test
    void mapToTaskDtoList(){
        //Given
         taskMapper = new TaskMapper();
        Task task = new Task(10L,"fufu","giyiig");
        List <Task> taskList = new ArrayList<>();
        taskList.add(task);

        //When
        List<TaskDto>taskDto =taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(10,taskDto.get(0).getTaskId());
    }
}
