package com.crud.tasks.controller;


import com.crud.tasks.domain.*;

import com.crud.tasks.service.TrelloFacadeS;
import com.crud.tasks.service.TrelloService;

import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TrelloController.class)
//@ExtendWith(MockitoExtension.class)
public class TrelloControllerTest {

    //@InjectMocks
    //TrelloController trelloController;
@Autowired
private MockMvc mockMvc;

    @MockBean
    private TrelloFacadeS trelloFacadeS;

    /*@Test
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
    }*/

    @Test
    void shouldFetchEmptyTrelloBoards() throws Exception {
        // Given
        when(trelloFacadeS.fetchTrelloBoards()).thenReturn(List.of());
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/trello/boards")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
}
    @Test
    void shouldFetchTrelloBoards() throws Exception {
        // Given
        List<TrelloListDto> trelloLists = List.of(new TrelloListDto("1", "Test list", false));
        List<TrelloBoardDto> trelloBoards = List.of(new TrelloBoardDto("1", "Test Task", trelloLists));
        when(trelloFacadeS.fetchTrelloBoards()).thenReturn(trelloBoards);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/trello/boards")
                        .contentType(MediaType.APPLICATION_JSON))
                // Trello board fields
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Test Task")))
                // Trello list fields
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists[0].id", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists[0].name", Matchers.is("Test list")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists[0].closed", Matchers.is(false)));
    }
    @Test
    void shouldCreateTrelloCard() throws Exception {
        // Given
        TrelloCardDto trelloCardDto =
                new TrelloCardDto("Test", "Test description", "top", "1");

        CreatedTrelloCardDto createdTrelloCardDto =
                new CreatedTrelloCardDto("232", "Test","http://test.com",new Badges());

        when(trelloFacadeS.createTrelloCard(any(TrelloCardDto.class))).thenReturn(createdTrelloCardDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(trelloCardDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/trello/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is("232")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shortUrl", Matchers.is("http://test.com")));



}}


