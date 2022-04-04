package ru.hj77.server.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.hj77.common.dto.ClientDTO;
import ru.hj77.server.entity.Card;
import ru.hj77.server.entity.Client;
import ru.hj77.server.exception.ClientNotFoundException;
import ru.hj77.server.repository.ClientRepository;
import ru.hj77.server.util.MappingUtils;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class ServerService {

    private ClientRepository clientCrudRepository;
    private MappingUtils mappingUtils;

    public ClientDTO getClient(Long id) {
        return mappingUtils.mapToClientDto(
                clientCrudRepository.findById(id)
                    .orElseThrow(ClientNotFoundException::new));
    }

    public List<ClientDTO> getAllClients() {
        return clientCrudRepository.findAll().stream().
                map(mappingUtils::mapToClientDto).collect(Collectors.toList());
    }

    public double getBalance(Long clientId, Long cardId) {
        return mappingUtils.mapToClientDto(clientCrudRepository.findById(clientId).orElseThrow(ClientNotFoundException::new))
                .getCardsDTOList().stream().filter(c -> cardId.equals(c.getId_card())).collect(Collectors.toList())
                .get(0)
                .getBalance();
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
