package ru.hj77.server;

import lombok.AllArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AllArgsConstructor
@WebMvcTest
@BootstrapWith
@SpringBootTest(classes = Application.class)
class ControllerTest {

    private MockMvc mockMvc;

    @Test
    void clientFount() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("api/clients/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers
                        .containsString("Daniil")));
    }
}
