//package ru.hj77.client.controller;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.junit.jupiter.MockitoExtension;
//import ru.hj77.client.dto.BalanceDTO;
//import ru.hj77.client.service.ClientService;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
//class AtmsControllerTest {
//
//    @Mock
//    private ClientService clientService;
//
//    @InjectMocks
//    AtmsController atmsController;
//
//    @Test
//    void testGetClientBalance() {
//        when(clientService.getClientBalance(anyLong(), anyLong(), anyInt()))
//                .thenReturn(new BalanceDTO(100.5));
//
//        atmsController.getClientBalance(1L, 1L, 2500);
//
//        verify(clientService).getClientBalance(1L, 1L, 2500);
//    }
//
//    @Test
//    void testWithdrawMoneyFromTheCard() {
//        when(clientService.withdrawMoneyFromTheCard(anyLong(), anyLong(), anyInt(), anyInt()))
//                .thenReturn(new BalanceDTO(100.5));
//
//        atmsController.withdrawMoneyFromTheCard(1L, 1L, 2500, 123);
//
//        verify(clientService).withdrawMoneyFromTheCard(1L, 1L, 2500, 123);
//    }
//
//    @Test
//    void testDepositMoneyFromTheCard() {
//        when(clientService.depositMoneyFromTheCard(anyLong(), anyLong(), anyInt(), anyInt()))
//                .thenReturn(new BalanceDTO(100.5));
//
//        atmsController.depositMoneyFromTheCard(1L, 1L, 2500, 123);
//
//        verify(clientService).depositMoneyFromTheCard(1L, 1L, 2500, 123);
//    }
//}