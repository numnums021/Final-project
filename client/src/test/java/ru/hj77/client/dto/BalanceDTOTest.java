package ru.hj77.client.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BalanceDTOTest {

    @Test
    void getBalance() {
        BalanceDTO balanceDTO = new BalanceDTO(100.0);

        double balance = balanceDTO.getBalance();

        assertEquals(100.0, balance);
    }
}