package ru.hj77.server.dto;

import lombok.Value;

import java.util.List;

@Value
public class ClientDTO {
    Long id_client;
    List<CardDTO> cardsDTOList;
}
