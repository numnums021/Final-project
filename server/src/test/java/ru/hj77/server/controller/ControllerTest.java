package ru.hj77.server.controller;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    public void getBalanceClientIdPin() {
        String url = "/card/balance";
        String body = "{" +
                "\"cardId\":\"6\",\n" +
                "\"pin\":\"110\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(url)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("{\"balance\":89020.11}")));
    }

    @SneakyThrows
    @Test
    public void authException() {
        String url = "/card/balance";
        String body = "{" +
                "\"cardId\":\"1\",\n" +
                "\"pin\":\"999\"\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(url)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError()
                );
    }

}
