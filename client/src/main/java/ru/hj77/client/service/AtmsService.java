package ru.hj77.client.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hj77.common.dto.BalanceDTO;
import ru.hj77.common.dto.RequestDTO;

@AllArgsConstructor
@Service
public class AtmsService {

    private RestTemplate restTemplate;

    public BalanceDTO getClientBalance(Long clientId, Long cardId, int pin) {
        HttpEntity<RequestDTO> request = new HttpEntity<>(new RequestDTO(clientId, cardId, pin));

        return restTemplate.postForObject("http://localhost:1703/card/getBalance/", request, BalanceDTO.class);
    }

    public BalanceDTO withdrawMoneyToCard(Long clientId, Long cardId, int money, int pin) {
        HttpEntity<RequestDTO> request = new HttpEntity<>(new RequestDTO(clientId, cardId, pin));

        return restTemplate.postForObject("http://localhost:1703/card/withdraw/" + money, request, BalanceDTO.class);
    }

    public BalanceDTO depositMoneyToCard(Long clientId, Long cardId, int money, int pin) {
        HttpEntity<RequestDTO> request = new HttpEntity<>(new RequestDTO(clientId, cardId, pin));

        return restTemplate.postForObject("http://localhost:1703/card/deposit/" + money, request, BalanceDTO.class);
    }
}
