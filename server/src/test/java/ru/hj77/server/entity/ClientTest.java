//package ru.hj77.server.entity;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ClientTest {
//
//    private Client client;
//
//    @BeforeEach
//    void setUp() {
//        this.client = new Client();
//    }
//
//    @Test
//    void getIdClient() {
//        Long idValue = 5L;
//        client.setId_client(idValue);
//        assertEquals(idValue, client.getId_client());
//    }
//
//    @Test
//    void getName() {
//        String name = "Ivan";
//        client.setName(name);
//        assertEquals(name, client.getName());
//    }
//
//    @Test
//    void getSurname() {
//        String name = "Girikov";
//        client.setSurname(name);
//        assertEquals(name, client.getSurname());
//    }
//
//    @Test
//    void getPatronymic() {
//        String patronymic = "Olegovich";
//        client.setPatronymic(patronymic);
//        assertEquals(patronymic, client.getPatronymic());
//    }
//
//    @Test
//    void getDate_of_birth() {
//        Date dateOfBirth = new Date(1999,6,3);
//        client.setDate_of_birth(dateOfBirth);
//        assertEquals(dateOfBirth, client.getDate_of_birth());
//    }
//
//    @Test
//    void getCards() {
//        List<Card> cardsList = Arrays.asList(
//                new Card(1L, 1111, 1232.11, client),
//                new Card(2L, 2222, 9800.5, client)
//        );
//        client.setCards(cardsList);
//        assertEquals(cardsList, client.getCards());
//    }
//
//}