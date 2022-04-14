package ru.hj77.server.controller;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    public void getBalanceTest() {
        String url = "/card/balance";
        String body = "{" +
                "\"cardId\":\"5\",\n" +
                "\"pin\":\"101\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(url)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("{\"balance\":1220.13}")));
    }

    @SneakyThrows
    @Test
    public void withdrawCardTest() {
        String url = "/card/withdraw";

        String body = "{" +
                "\"cardId\":\"6\",\n" +
                "\"pin\":\"110\", \n" +
                "\"money\":\"1\"\n" +
                "}";

        double balance = 89026.11 + 1;
        mockMvc.perform(MockMvcRequestBuilders
                        .post(url)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"balance\":" + balance + "}"));
    }

    @SneakyThrows
    @Test
    public void depositCardTest() {
        String url = "/card/deposit";

        String body = "{" +
                "\"cardId\":\"7\",\n" +
                "\"pin\":\"111\", \n" +
                "\"money\":\"1\"\n" +
                "}";

        double balance = 9.5 - 1;
        mockMvc.perform(MockMvcRequestBuilders
                        .post(url)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"balance\":" + balance + "}"));
    }

    @SneakyThrows
    @Test
    public void depositCardExceptionTest() {
        String url = "/card/deposit";

        String body = "{" +
                "\"cardId\":\"7\",\n" +
                "\"pin\":\"111\", \n" +
                "\"money\":\"9999\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(url)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @SneakyThrows
    @Test
    public void authExceptionTest() {
        String url = "/card/balance";
        String body = "{" +
                "\"cardId\":\"1\",\n" +
                "\"pin\":\"999\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(url)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

}
