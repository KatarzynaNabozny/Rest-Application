package com.crud.tasks;

import com.crud.tasks.domain.TaskDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TasksApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        TaskDto taskDto = new TaskDto(
                 1L,
                "Test title",
                "I want to be a coder");

        Long taskId = taskDto.getTaskId();
        String title = taskDto.getTitle();
        String content = taskDto.getContent();
        System.out.println(taskId + " " + title + " " + content);

        SpringApplication.run(TasksApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TasksApplication.class);
    }
}
