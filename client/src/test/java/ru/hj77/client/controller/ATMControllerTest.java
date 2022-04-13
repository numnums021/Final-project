//package ru.hj77.client.controller;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.junit.jupiter.MockitoExtension;
//import ru.hj77.client.service.AtmsService;
//import ru.hj77.common.communication.response.Response;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
//class ATMControllerTest {
//
//    @InjectMocks
//    ATMController ATMController;
//
//    @Mock
//    AtmsService service;
//
//    @Test
//    void testGetClientBalance() {
//        when(service.getClientBalance(anyLong(), anyInt()))
//                .thenReturn(new Response(1));
//
//        ATMController.getClientBalance(1L, 0);
//
//        verify(service).getClientBalance(1L, 0);
//    }
//
//    @Test
//    void testWithdrawMoneyFromTheCard() {
//        when(service.withdrawMoneyToCard(anyLong(), anyInt(), anyInt()))
//                .thenReturn(new Response(1));
//
//        ATMController.withdrawMoneyToCard(1L, 1, 0);
//
//        verify(service).withdrawMoneyToCard(1L, 1, 0);
//    }
//
//    @Test
//    void testDepositMoneyFromTheCard() {
//        when(service.depositMoneyToCard(anyLong(), anyInt(), anyInt()))
//                .thenReturn(new Response(1));
//
//        ATMController.depositMoneyToCard(1L, 1, 0);
//
//        verify(service).depositMoneyToCard(1L, 1, 0);
//    }
//}