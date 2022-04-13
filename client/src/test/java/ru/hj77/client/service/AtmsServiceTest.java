//package ru.hj77.client.service;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//import ru.hj77.common.communication.response.Response;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
//class AtmsServiceTest {
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @InjectMocks
//    AtmsService service;
//
//    @Test
//    void testGetClientBalance() {
//        Response expectedResponse = new Response(10);
//
//        when(restTemplate.postForObject(anyString(), any(), any()))
//                .thenReturn(expectedResponse);
//
//        Response actualResponse = service.getClientBalance(1L, 0);
//
//        assertEquals(expectedResponse, actualResponse);
//    }
//
//    @Test
//    void testWithdrawMoneyToCard() {
//        Response expectedResponse = new Response(10);
//
//        when(restTemplate.postForObject(anyString(), any(), any()))
//                .thenReturn(expectedResponse);
//
//        Response actualResponse = service.withdrawMoneyToCard(1L, 10,0);
//
//        assertEquals(expectedResponse, actualResponse);
//    }
//
//    @Test
//    void testDepositMoneyToCard() {
//        Response expectedResponse = new Response(10);
//
//        when(restTemplate.postForObject(anyString(), any(), any()))
//                .thenReturn(expectedResponse);
//
//        Response actualResponse = service.depositMoneyToCard(1L, 10,0);
//
//        assertEquals(expectedResponse, actualResponse);
//
//    }
//}