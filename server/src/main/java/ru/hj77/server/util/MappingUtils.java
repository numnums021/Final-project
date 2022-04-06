package ru.hj77.server.util;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.hj77.server.dto.CardDTO;
import ru.hj77.server.dto.ClientDTO;
import ru.hj77.server.entity.Card;
import ru.hj77.server.entity.Client;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingUtils {

    public ClientDTO mapToClientDto(@NonNull Client client) {

        List<Card> cardList = client.getCards();
        List<CardDTO> cardDTOList = new ArrayList<>();

        for (Card card : cardList) {
            cardDTOList.add(new CardDTO(
                    card.getId_card(),
                    card.getPin(),
                    card.getBalance())
            );
        }

        return new ClientDTO(
                client.getId_client(),
                client.getName(),
                client.getSurname(),
                client.getPatronymic(),
                client.getDate_of_birth(),
                cardDTOList
        );
    }

}
