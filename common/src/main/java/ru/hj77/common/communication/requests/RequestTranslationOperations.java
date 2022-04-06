package ru.hj77.common.communication.requests;

import lombok.Value;

@Value
public class RequestTranslationOperations {
    Long idClientTransferringCash;
    Long idClientReceivingCash;
    Long idCardTransferring;
    Long idCardReceiving;
    int money;
}
