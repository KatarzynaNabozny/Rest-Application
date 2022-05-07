package com.crud.tasks.controller;


import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;

import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloFacadeS;
import com.crud.tasks.service.TrelloService;

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
public class TrelloControllerTest {

    @InjectMocks
    TrelloController trelloController;

    @Mock
    private TrelloFacadeS trelloFacadeS;

    @Test
    void getTrelloBoards() {
        //Given
        TrelloController trelloController = new TrelloController(trelloFacadeS);
        //When
        ResponseEntity<List<TrelloBoardDto>> listResponseEntity = trelloController.getTrelloBoards();
        //Then
        assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());
    }

    @Test
    void createTrelloCards() {
        //Given
        TrelloController trelloController = new TrelloController(trelloFacadeS);
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        //When
        ResponseEntity<CreatedTrelloCardDto> list = trelloController.createTrelloCard(trelloCardDto);
        //Then
        assertEquals(HttpStatus.OK, list.getStatusCode());
    }
}


