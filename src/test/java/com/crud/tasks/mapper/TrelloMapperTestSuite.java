package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.trello.client.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloListDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards(){
        //Given
        TrelloBoardDto trelloBoard1 = new TrelloBoardDto();
        TrelloBoardDto trelloBoard2 = new TrelloBoardDto("2", "test2", new ArrayList<>());
        trelloBoard1.setId("1");
        trelloBoard1.setName("test");
        trelloBoard1.setLists(new ArrayList<>());
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);
        //When
        List<TrelloBoard> output = trelloMapper.mapToBoards(trelloBoards);
        TrelloBoard trelloBoard = output.get(0);
        //Then
        assertEquals(2,output.size());
        assertEquals(trelloBoard1.getId(), trelloBoard.getId());
        assertEquals(trelloBoard1.getName(), trelloBoard.getName());
    }

    @Test
    public void testMapToBoardsDto(){
        //Given
        TrelloBoard trelloBoard1 = new TrelloBoard("1","test", new ArrayList<>());
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "test2", new ArrayList<>());
        List<TrelloBoard> trelloBoards = new ArrayList<>(){{
            add(trelloBoard1);
            add(trelloBoard2);
        }};
        //When
        List<TrelloBoardDto> output = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(2,output.size());
    }

    @Test
    public void testMapToList(){
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "test", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "test1", false);
        List<TrelloListDto> trelloListDtos = new ArrayList<>(){{
            add(trelloListDto1);
            add(trelloListDto2);
        }};
        //When
        List<TrelloList> output = trelloMapper.mapToList(trelloListDtos);
        //Then
        assertEquals(2,output.size());
    }

    @Test
    public void testMapToListDto(){
        //Given
        TrelloList trelloList1 = new TrelloList("1", "test", false);
        TrelloList trelloList2 = new TrelloList("2", "test1", false);
        List<TrelloList> trelloList = new ArrayList<>(){{
            add(trelloList1);
            add(trelloList2);
        }};
        //When
        List<TrelloListDto> output = trelloMapper.mapToListDto(trelloList);
        //Then
        assertEquals(2,output.size());
    }

    @Test
    public void testMapToCard(){
        //Given
        TrelloCard trelloCard = new TrelloCard("test","test","test","1");
        //when
        TrelloCardDto output = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("test", output.getName());
    }

    @Test
    public void testMapToCardDto(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test","test","test","1");
        //when
        TrelloCard output = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("test", output.getName());
    }

}