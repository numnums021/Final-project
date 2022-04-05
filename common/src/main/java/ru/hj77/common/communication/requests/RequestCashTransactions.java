package ru.hj77.common.communication.requests;

import lombok.Value;

@Value
public class RequestCashTransactions {
    Long cardId;
    int pin;
    int money;
}
