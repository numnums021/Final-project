package ru.hj77.server.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hj77.server.entities.Card;
import ru.hj77.server.entities.Client;
import ru.hj77.server.entities.Role;
import ru.hj77.server.repositories.CardRepository;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class ServerServiceTest {

    @Mock
    CardRepository cardRepository;

    @InjectMocks
    private CardService service;

    Collection<Role> roles = new ArrayList<>();

    @Test
    void testGetBalance(){
        when(cardRepository.findById(anyLong()))
                .thenReturn(java.util.Optional.of(
                        new Card(1L, "11", 10, new Client(), roles)));

        double actualBalance = service.getBalance(1L);

        assertEquals(10 ,actualBalance);
    }

    @Test
    void testWithdrawMoneyFromTheCard() {
        when(cardRepository.findById(anyLong()))
                .thenReturn(java.util.Optional.of(
                        new Card(1L, "11", 10, new Client(), roles)));

        double actualBalance = service.withdrawMoneyFromTheCard(1L, 10);

        assertEquals(20 ,actualBalance);
    }

    @Test
    void testDepositMoneyFromTheCard() {
        when(cardRepository.findById(anyLong()))
                .thenReturn(java.util.Optional.of(
                        new Card(1L, "11", 10, new Client(), roles)));

        double actualBalance = service.depositMoneyFromTheCard(1L, 10);

        assertEquals(0 ,actualBalance);
    }
}