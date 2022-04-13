package ru.hj77.client.service;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hj77.common.communication.Response;
import ru.hj77.common.communication.requests.BasicCashTransactions;
import ru.hj77.common.communication.requests.RequestCashTransactions;
import ru.hj77.common.communication.security.AuthenticationRequest;
import ru.hj77.common.communication.security.AuthenticationResponse;

@Log
@AllArgsConstructor
@Service
public class AtmsService {

    private RestTemplate restTemplate;

    public Response getClientBalance(AuthenticationRequest authInfo) {
        HttpEntity<BasicCashTransactions> request =
                new HttpEntity<>(new BasicCashTransactions(Long.parseLong(authInfo.getUsername())),
                        getHeaders(authInfo));

        return restTemplate.postForObject(
                "http://localhost:1703/card/balance/", request, Response.class);
    }

    public Response withdrawMoneyToCard(AuthenticationRequest authInfo, int money) {
        HttpEntity<RequestCashTransactions> request = new HttpEntity<>(
                new RequestCashTransactions(Long.parseLong(authInfo.getUsername()), money),
                getHeaders(authInfo));

        return restTemplate.postForObject(
                "http://localhost:1703/card/withdraw/", request, Response.class);
    }

    public Response depositMoneyToCard(AuthenticationRequest authInfo, int money) {
        HttpEntity<RequestCashTransactions> request = new HttpEntity<>(
                new RequestCashTransactions(Long.parseLong(authInfo.getUsername()), money)
                , getHeaders(authInfo));

        return restTemplate.postForObject(
                "http://localhost:1703/card/deposit/", request, Response.class);
    }

    private HttpHeaders getHeaders(AuthenticationRequest authInfo) {
        AuthenticationResponse response = restTemplate.postForObject(
                "http://localhost:1703/auth", authInfo, AuthenticationResponse.class);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Authorization", "Bearer " + response.getToken());

        return responseHeaders;
    }
}
