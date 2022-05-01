package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloMapperTest {

    @Test
    void mapToBoards() {
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto();
        trelloBoardDto.setName("Nowy");

        TrelloListDto trelloListDto = new TrelloListDto("sf", "fsdf", true);
        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(trelloListDto);
        trelloBoardDto.setLists(lists);

        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);
        //When
        List<TrelloBoard> resultTrelloBoards = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        assertEquals("Nowy", resultTrelloBoards.get(0).getName());
    }

    @Test
    void mapToBoardsDto() {
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloList trelloList = new TrelloList("sf", "fsdf", true);
        List<TrelloList> list = new ArrayList<>();
        list.add(trelloList);
        TrelloBoard trelloBoard = new TrelloBoard("id", "name", list);

        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard);
        //When
        List<TrelloBoardDto> resultTrelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoardList);
        //Then
        assertEquals("name", resultTrelloBoardsDto.get(0).getName());
    }

    @Test
    void mapToList() {
        //Given

        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloListDto trelloListDto = new TrelloListDto("sf", "fsdf", true);
        List<TrelloListDto> trelloList = new ArrayList<>();
        trelloList.add(trelloListDto);

        //When

        List<TrelloList> result = trelloMapper.mapToList(trelloList);

        //Then

        assertEquals("fsdf", result.get(0).getName());
    }

    @Test
    void mapToListDto(){
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloList trelloList = new TrelloList("fwsef","fwf",true);
        List<TrelloList>trelloList2 = new ArrayList<>();
        trelloList2.add(trelloList);
        //When
        List<TrelloListDto>result = trelloMapper.mapToListDto(trelloList2);
        //Then
        assertEquals("fwsef",result.get(0).getId());
    }

    @Test
    void mapToCardDto(){
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloCard trelloCard = new TrelloCard("fkyfki","guyhguyg","hgiuhgi","fuyfiu");

        //When
       TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("fkyfki",trelloCardDto.getName());
    }

    @Test
    void mapToCard(){
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloCardDto trelloCardDto = new TrelloCardDto("fkyfki","guyhguyg","hgiuhgi","fuyfiu");

        //When
       TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("fkyfki",trelloCard.getName());
    }

}
