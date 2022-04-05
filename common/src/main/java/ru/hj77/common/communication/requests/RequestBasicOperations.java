package ru.hj77.common.communication.requests;

import lombok.Value;

@Value
public class RequestBasicOperations {
    Long cardId;
    int pin;
}
