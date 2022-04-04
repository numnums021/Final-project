package ru.hj77.server.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.hj77.server.entity.Card;
import ru.hj77.server.entity.Client;
import ru.hj77.server.exception.ClientNotFoundException;
import ru.hj77.server.repository.ClientRepository;

import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class CardService {

    private ClientRepository clientCrudRepository;

    public double getBalance(Long clientId, Long cardId) {
        Client client = clientCrudRepository.findById(clientId)
                .orElseThrow(ClientNotFoundException::new);

        return searchCardByCardId(client, cardId).getBalance();
    }

    public double withdrawMoneyFromTheCard(Long clientId, Long cardId, double money){
        Client client = clientCrudRepository.findById(clientId)
                .orElseThrow(ClientNotFoundException::new);

        Card card = searchCardByCardId(client, cardId);
        card.setBalance(searchCardByCardId(client, cardId).getBalance() + money);

        clientCrudRepository.save(client);

        return card.getBalance();
    }

    public double depositMoneyFromTheCard(Long clientId, Long cardId, double money) {
        Client client = clientCrudRepository.findById(clientId)
                .orElseThrow(ClientNotFoundException::new);

        Card card = searchCardByCardId(client, cardId);

        card.setBalance(card.getBalance() - money);
        clientCrudRepository.save(client);

        return card.getBalance();
    }

    public Card searchCardByCardId(@NonNull Client client, Long cardId) {
        return client.getCards().stream().
                filter(c -> c.getId_card().equals(cardId)).
                collect(Collectors.toList()).get(0);
    }

}
