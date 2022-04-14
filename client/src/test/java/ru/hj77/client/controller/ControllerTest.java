package ru.hj77.client.controller;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import ru.hj77.common.communication.Response;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @SneakyThrows
    @Test
    public void getBalanceTest() {
        when(restTemplate.postForObject(anyString(), ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(new Response(500.5));

        Long cardId = 1L;
        int pin = 1;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/atms/cards/{cardId}/pin/{pin}", cardId, pin)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ваш баланс = 500.5 руб.")));
    }

    @SneakyThrows
    @Test
    public void withdrawCardTest() {
        when(restTemplate.postForObject(anyString(), ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(new Response(500.5));

        Long cardId = 1L;
        int pin = 1;
        String money = "20";

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/atms/withdraw/cards/{cardId}/pin/{pin}/money/{money}", cardId, pin, money)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Вы внесли денежные средства на карту. " +
                        "Ваш баланс составляет: 500.5 руб.")));
    }

    @SneakyThrows
    @Test
    public void depositCardTest() {
        when(restTemplate.postForObject(anyString(), ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(new Response(500.5));

        Long cardId = 1L;
        int pin = 1;
        String money = "20";

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/atms/deposit/cards/{cardId}/pin/{pin}/money/{money}", cardId, pin, money)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Вы сняли денежные средства с карты. " +
                        "Ваш баланс составляет: 500.5 руб.")));
    }

}
