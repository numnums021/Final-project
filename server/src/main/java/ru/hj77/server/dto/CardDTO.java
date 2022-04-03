package ru.hj77.server.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CardDTO {
    final Long id_card;
    final int pinCode;
    final double balance;
}
