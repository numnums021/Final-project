package ru.hj77.client.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hj77.common.communication.Response;
import ru.hj77.common.communication.requests.RequestBasicOperations;
import ru.hj77.common.communication.requests.RequestCashTransactions;

@AllArgsConstructor
@Service
public class AtmsService {

    private RestTemplate restTemplate;

    public Response getClientBalance(Long cardId, int pin) {
        HttpEntity<RequestBasicOperations> request =
                new HttpEntity<>(new RequestBasicOperations(cardId, pin));

        return restTemplate.postForObject(
                "http://localhost:1703/card/getBalance/", request, Response.class);
    }

    public Response withdrawMoneyToCard(Long cardId, int money, int pin) {
        HttpEntity<RequestCashTransactions> request = new HttpEntity<>(
                new RequestCashTransactions(cardId, pin, money));

        return restTemplate.postForObject(
                "http://localhost:1703/card/withdraw/", request, Response.class);
    }

    public Response depositMoneyToCard(Long cardId, int money, int pin) {
        HttpEntity<RequestCashTransactions> request = new HttpEntity<>(
                new RequestCashTransactions(cardId, pin, money));

        return restTemplate.postForObject(
                "http://localhost:1703/card/deposit/", request, Response.class);
    }
}
