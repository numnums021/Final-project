package ru.hj77.server.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CardDTO {
    final int id_card;
    final int pinCode;
    final double balance;
}
