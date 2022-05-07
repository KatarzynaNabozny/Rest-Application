package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalHttpErrorHandlerTest {

    @Test
      void handleTaskNotFoundException() {
        //Given
        GlobalHttpErrorHandler globalHttpErrorHandler = new GlobalHttpErrorHandler();
        TaskNotFoundException exception1 = new TaskNotFoundException();
        //When
        ResponseEntity<Object> responseEntity = globalHttpErrorHandler.handleTaskNotFoundException(exception1);
        //Then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}

