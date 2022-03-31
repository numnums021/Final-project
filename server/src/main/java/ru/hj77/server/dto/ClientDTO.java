package ru.hj77.server.dto;

import lombok.Value;

import java.util.Date;
import java.util.List;

@Value
public class ClientDTO {
    int id_client;
    String name;
    String surname;
    String patronymic;
    Date date_of_birth;
    List<CardDTO> cardsDTOList;
}
