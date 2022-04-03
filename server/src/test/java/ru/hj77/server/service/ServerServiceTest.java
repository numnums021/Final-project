package ru.hj77.server.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hj77.server.dto.ClientDTO;
import ru.hj77.server.entity.Card;
import ru.hj77.server.entity.Client;
import ru.hj77.server.repository.ClientRepository;
import ru.hj77.server.util.MappingUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class ServerServiceTest {

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    private ServerService service;

    @Test
    void testWithdrawMoneyFromTheCard() {
        Client client = new Client();
        client.setId_client(1L);

        List<Card> cardsList = Arrays.asList(
                new Card(1L, 1111, 1232.0, client),
                new Card(2L, 2222, 9800.5, client)
        );

        client.setCards(cardsList);

        when(clientRepository.findById(anyLong()))
                .thenReturn(java.util.Optional.of(client));

        assertEquals(10000.5,
                service.withdrawMoneyFromTheCard(1L, 2L, 200));
    }

    @Test
    void testDepositMoneyFromTheCard() {
        Client client = new Client();
        client.setId_client(1L);

        List<Card> cardsList = Arrays.asList(
                new Card(1L, 1111, 1232, client),
                new Card(2L, 2222, 9800.5, client)
        );

        client.setCards(cardsList);

        when(clientRepository.findById(anyLong()))
                .thenReturn(java.util.Optional.of(client));

        assertEquals(1000.0,
                service.depositMoneyFromTheCard(1L, 1L, 232));
    }

    @Test
    void testSearchCardByCardId() {
        Client client = new Client();
        client.setId_client(1L);

        Card card = new Card(2L, 2222, 9800.5, client);

        List<Card> cardsList = Arrays.asList(
                new Card(1L, 1111, 1232, client),
                card
        );

        client.setCards(cardsList);

        assertEquals(card,
                service.searchCardByCardId(client, 2L));
    }
}