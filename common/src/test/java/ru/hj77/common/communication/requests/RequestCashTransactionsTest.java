//package ru.hj77.common.communication.requests;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class RequestCashTransactionsTest {
//
//    private RequestCashTransactions request;
//
//    @BeforeEach
//    void setUp() {
//        this.request = new RequestCashTransactions(1L, 123, 111);
//    }
//
//    @Test
//    void getCardId() {
//        assertEquals(1L, request.getCardId());
//    }
//
//    @Test
//    void getPin() {
//        assertEquals(123, request.getPin());
//    }
//
//    @Test
//    void getMoney() {
//        assertEquals(111, request.getMoney());
//    }
//}