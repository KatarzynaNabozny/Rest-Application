package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTest {

    private static String SUBJECT = "Tasks: New Trello card";

    @InjectMocks
    private TrelloService trelloService;
    @Mock
    private TrelloClient trelloClient;
    @Mock
    private SimpleEmailService emailService;
    @Mock
    private AdminConfig adminConfig;


    @Test
    void fetchTrelloBoards() {

        //Given
        trelloService = new TrelloService(trelloClient, emailService, adminConfig);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto();
        trelloBoardDto.setName("gol");
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);
        given(trelloClient.getTrelloBoards()).willReturn(trelloBoardDtoList);
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();
        //Then
        assertEquals("gol", trelloBoardDtos.get(0).getName());
    }


    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        ofNullable(newCard).ifPresent(card -> emailService.send(
                Mail.builder()
                        .mailTo(adminConfig.getAdminMail())
                        .subject(SUBJECT)
                        .message("New card: " + trelloCardDto.getName() + " has been created on your Trello account")
                        .toCc(null)
                        .build()
        ));
        return newCard;
    }

    @Test
    void createTrelloCard() {
        //given
        trelloService = new TrelloService(trelloClient, emailService, adminConfig);
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        CreatedTrelloCardDto newCard = new CreatedTrelloCardDto();
        newCard.setName("fws");
        given(trelloClient.createNewCard(trelloCardDto)).willReturn(newCard);
        //when
        CreatedTrelloCardDto trelloCard = trelloService.createTrelloCard(trelloCardDto);
        //then
        assertEquals("fws", trelloCard.getName());

        verify(emailService,times(1)).send(
                Mail.builder()
                .mailTo(null)
                .subject(SUBJECT)
                .message("New card: " + trelloCardDto.getName() + " has been created on your Trello account")
                .toCc(null)
                .build());
    }
}