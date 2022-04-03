package ru.hj77.server.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    private Card card;

    @BeforeEach
    void setUp() {
        this.card = new Card();
    }

    @Test
    void getId_card() {
        Long idValue = 5L;

        card.setId_card(idValue);

        assertEquals(idValue, card.getId_card());
    }

    @Test
    void getPinCode() {
        int pinCode = 1111;

        card.setPinCode(pinCode);

        assertEquals(pinCode, card.getPinCode());
    }

    @Test
    void getBalance() {
        double balance = 190555.5;

        card.setBalance(balance);

        assertEquals(balance, card.getBalance());
    }

    @Test
    void getId_client() {
        Client client = new Client();

        card.setId_client(client);

        assertEquals(client, card.getId_client());
    }
}