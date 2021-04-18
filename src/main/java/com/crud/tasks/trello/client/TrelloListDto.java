package com.crud.tasks.trello.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.management.ConstructorParameters;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloListDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("closed")
    private boolean isClosed;
}
