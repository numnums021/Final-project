package ru.hj77.server.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hj77.server.services.CardService;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class CardControllerTest {

    @Mock
    private CardService service;

    @InjectMocks
    private CardController controller;

    @Test
    void testGetBalance() {

//        when(service.getBalance(anyLong()))
//                .thenReturn(10.0);
//
//        controller.getBalance(new BasicCashTransactions(1L));
//
//        verify(service).getBalance(1L);
    }

    @Test
    void testWithdrawMoneyFromTheCard() {
        when(service.withdrawMoneyFromTheCard(anyLong(), anyDouble()))
                .thenReturn(10.5);

//        controller.withdrawMoneyFromTheCard(new RequestCashTransactions(1L, 10));

        verify(service).withdrawMoneyFromTheCard( 1L, 10.0);
    }

    @Test
    void testDepositMoneyFromTheCard() {

//        when(service.depositMoneyFromTheCard(anyLong(), anyDouble()))
//                .thenReturn(10.5);
//
//        controller.depositMoneyFromTheCard(new RequestCashTransactions(1L, 10));
//
//        verify(service).depositMoneyFromTheCard( 1L, 10.0);
    }
}