package ru.hj77.common.dto;

import lombok.Value;

@Value
public class RequestDTO {
    Long clientId;
    Long cardId;
    int pin;
}
