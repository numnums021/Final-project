package ru.hj77.common.communication;

import lombok.Value;

@Value
public class Request {
    Long cardId;
    int pin;
}
