package ru.hj77.client.service;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hj77.common.communication.Response;
import ru.hj77.common.communication.requests.RequestBasicOperations;
import ru.hj77.common.communication.requests.RequestCashTransactions;
import ru.hj77.common.communication.security.AuthenticationRequest;
import ru.hj77.common.communication.security.AuthenticationResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
@Log
@AllArgsConstructor
@Service
public class AtmsService {

    private RestTemplate restTemplate;

    public Response getClientBalance(Long cardId, int pin) {
        HttpEntity<RequestBasicOperations> request =
                new HttpEntity<>(new RequestBasicOperations(cardId, pin));

        return restTemplate.postForObject(
                "http://localhost:1703/card/balance/", request, Response.class);
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

    public ResponseEntity<Response> test(String token) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Authorization", "Barer " + token);

        HttpEntity<RequestBasicOperations> request =
                new HttpEntity<>(new RequestBasicOperations(1L, 11),
                        responseHeaders);

        log.info(request.getHeaders().toString());
        return ResponseEntity.ok(Objects.requireNonNull(restTemplate.postForObject(
                "http://localhost:1703/test/", request, Response.class)));
    }
}
