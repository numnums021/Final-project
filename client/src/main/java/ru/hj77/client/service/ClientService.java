package ru.hj77.client.service;

import org.springframework.stereotype.Service;
import ru.hj77.client.dto.BalanceDTO;


@Service
public class ClientService {

    public BalanceDTO getClientBalance(Long clientId, Long cardId, int Pin) {
        return new BalanceDTO(10.0);
    }
}
