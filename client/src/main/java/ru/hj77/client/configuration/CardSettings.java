package ru.hj77.client.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
public class CardSettings {
    @Value("${atms.auth.cardId}")
    String cardId;

    @Value("${atms.auth.pin}")
    String pin;
}
