package com.crud.tasks.service;

import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.trello.client.CreatedTrelloCardDto;
import com.crud.tasks.trello.client.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTestSuite {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Test
    public void testFetchTrelloBoards(){
        //Given
        TrelloCardDto trelloCardDto =
                new TrelloCardDto("Test", "Test description", "top", "1");

        CreatedTrelloCardDto createdTrelloCardDto =
                new CreatedTrelloCardDto("232", "Test", "http://test.com");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto output = trelloService.createTrelloCard(trelloCardDto);
        //Then
        assertEquals("232", output.getId());
        assertEquals("Test", output.getName());
        assertEquals("http://test.com", output.getShortUrl());
    }

}