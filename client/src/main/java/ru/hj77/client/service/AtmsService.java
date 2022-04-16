package ru.hj77.client.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hj77.client.configuration.CardSettings;
import ru.hj77.common.communication.requests.*;
import ru.hj77.common.communication.response.*;
import ru.hj77.common.security.AuthenticationRequest;
import ru.hj77.common.security.AuthenticationResponse;

import java.util.Objects;

@AllArgsConstructor
@Service
public class AtmsService {

    private RestTemplate restTemplate;
    private final CardSettings card;
    private String token;

    public BasicResponse getClientBalance() {
        HttpEntity<BasicCashTransactions> request =
                new HttpEntity<>(new BasicCashTransactions(Long.parseLong(card.getCardId())), getHeaders());

        return restTemplate.postForObject("http://localhost:1703/card/balance/", request, BasicResponse.class);
    }

    public BasicResponse withdrawMoneyToCard(int money) {
        HttpEntity<RequestCashTransactions> request =
                new HttpEntity<>(new RequestCashTransactions(Long.parseLong(card.getCardId()), money), getHeaders());

        return restTemplate.postForObject("http://localhost:1703/card/withdraw/", request, BasicResponse.class);
    }

    public BasicResponse depositMoneyToCard(int money) {
        HttpEntity<RequestCashTransactions> request = new HttpEntity<>(
                        new RequestCashTransactions(Long.parseLong(card.getCardId()), money), getHeaders());

        return restTemplate.postForObject("http://localhost:1703/card/deposit/", request, BasicResponse.class);
    }

    public String auth(){
        AuthenticationResponse response = restTemplate.postForObject(
                "http://localhost:1703/auth", new AuthenticationRequest(card.getCardId(), card.getPin()), AuthenticationResponse.class);
        if (Objects.requireNonNull(response).getToken() != null) {
            token = "Bearer " + response.getToken();
            return "успешно";
        }
        return "неудачно";
    }

    public void logout(){
        token = "";
    }

    private HttpHeaders getHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Authorization", token);
        return responseHeaders;
    }
}
