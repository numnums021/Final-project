package ru.hj77.server.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    private Client client;

    @BeforeEach
    void setUp() {
        this.client = new Client();
    }

    @Test
    void getIdClient() {
        Long idValue = 5L;
        client.setId_client(idValue);
        assertEquals(idValue, client.getId_client());
    }

    @Test
    void getName() {
        String name = "Ivan";
        client.setName(name);
        assertEquals(name, client.getName());
    }

    @Test
    void getSurname() {
        String name = "Girikov";
        client.setSurname(name);
        assertEquals(name, client.getSurname());
    }

    @Test
    void getPatronymic() {
        String patronymic = "Olegovich";
        client.setPatronymic(patronymic);
        assertEquals(patronymic, client.getPatronymic());
    }

    @Test
    void getDate_of_birth() {
        Date dateOfBirth = new Date(1999,6,3);
        client.setDate_of_birth(dateOfBirth);
        assertEquals(dateOfBirth, client.getDate_of_birth());
    }

    @Test
    void getCards() {
        Collection<Role> roles = new ArrayList<>();
        List<Card> cardsList = Arrays.asList(
                new Card(1L, "11", 1232.11, client, roles),
                new Card(2L, "22", 9800.5, client, roles)
        );
        client.setCards(cardsList);
        assertEquals(cardsList, client.getCards());
    }

}