package ru.hj77.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hj77.server.entity.Client;
import ru.hj77.server.repository.ClientRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(ClientController.class)
class ClientControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ClientRepository clientRepository;

    Client client_1 = new Client();
    Client client_2 = new Client();
    Client client_3 = new Client();

    @Test
    public void getAllRecords_success() throws Exception {
        List records = new ArrayList<>(Arrays.asList(client_1, client_2, client_3));

        Mockito.when(clientRepository.findAll())
                .thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/info/clients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

}
