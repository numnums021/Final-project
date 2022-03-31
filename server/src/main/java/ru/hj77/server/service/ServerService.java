package ru.hj77.server.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.hj77.common.OperationTypes;
import ru.hj77.common.Response;
import ru.hj77.server.dto.CardDTO;
import ru.hj77.server.dto.ClientDTO;
import ru.hj77.server.entity.Card;
import ru.hj77.server.entity.Client;
import ru.hj77.server.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Service
public class ServerService {

    private ClientRepository clientCrudRepository;

    public ClientDTO getClient(Long id) {

        Client client = clientCrudRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        List<Card> cardList = client.getCards();
        List<CardDTO> cardDTOList = new ArrayList<>();

        for (Card card : cardList) {

            cardDTOList.add(new CardDTO(
                    card.getId_card().intValue(),
                    card.getPinCode(),
                    card.getBalance())
            );
        }

        return new ClientDTO(
                client.getId_client().intValue(),
                client.getName(), client.getSurname(), client.getPatronymic(),
                client.getDate_of_birth(),
                cardDTOList);
    }

    public List<ClientDTO> getAllClients() {

        Iterable<Client> clientIterable = clientCrudRepository.findAll();
        List<ClientDTO> clients = new ArrayList<>();

        clientIterable.forEach(
                client -> {
                    List<Card> cardList = client.getCards();
                    List<CardDTO> cardDTOList = new ArrayList<>();

                    for (Card card : cardList) {

                        cardDTOList.add(new CardDTO(
                                card.getId_card().intValue(),
                                card.getPinCode(),
                                card.getBalance())
                        );
                    }
                    clients.add(new ClientDTO(
                            client.getId_client().intValue(),
                            client.getName(), client.getSurname(), client.getPatronymic(),
                            client.getDate_of_birth(),
                            cardDTOList));
                }
        );
        return clients;
    }

    public double moneyOperationsWithCardClient(Long clientId, Long cardId, double money, OperationTypes operationTypes) {
        Client client = clientCrudRepository.findById(clientId)
                .orElseThrow(RuntimeException::new);

        Card card = searchCardByCardId(client, cardId);

        switch (operationTypes) {
            case WITHDRAW:
                card.setBalance(card.getBalance() + money);
                break;
            case DEPOSIT:
                card.setBalance(card.getBalance() - money);
                break;
        }

        clientCrudRepository.save(client);

        return card.getBalance();
    }

    public Card searchCardByCardId(@NonNull Client client, Long cardId) {

        List<Card> cardList = client.getCards();

        for (Card card : cardList)
            if (card.getId_card().equals(cardId))
                return card;

        return null;
    }
}
