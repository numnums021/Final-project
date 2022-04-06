package ru.hj77.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.hj77.common.communication.requests.RequestBasicOperations;
import ru.hj77.server.entity.Card;
import ru.hj77.server.entity.Client;
import ru.hj77.server.repository.CardRepository;
import ru.hj77.server.service.CardService;
import ru.hj77.server.service.SecurityService;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(CardController.class)
public class AdditionallyCardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    CardRepository cardRepository;

    @MockBean
    CardService cardService;

    @MockBean
    SecurityService securityService;

    Client CLIENT_1 = new Client();
    Client CLIENT_2 = new Client();

    Card CARD_1 = new Card(1l, 0, 100, CLIENT_1);
    Card CARD_2 = new Card(2l, 1, 200, CLIENT_1);
    Card CARD_3 = new Card(3l, 2, 300, CLIENT_2);

    @Test
    public void getClientBalanceTest() throws Exception {
        when(securityService.cardIsAuth(anyLong(), anyInt()))
                .thenReturn(true);

        when(cardRepository.findById(anyLong()))
                .thenReturn(java.util.Optional.ofNullable(CARD_1));

        RequestBasicOperations request = new RequestBasicOperations(1L, 0);

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        mockMvc.perform();

//
//        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/card/getbalance")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(this.mapper.writeValueAsString(request));
//
//        mockMvc.perform(mockRequest)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", notNullValue()));
//                .andExpect(jsonPath("$.name", is("John Doe")));
    }


}
