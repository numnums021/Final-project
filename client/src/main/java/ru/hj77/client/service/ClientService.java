package ru.hj77.client.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hj77.client.dto.BalanceDTO;
import ru.hj77.common.*;

@Log4j2
@Service
public class ClientService {

    public BalanceDTO getClientBalance(Long clientId, Long cardId, int Pin) {

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Request> request = new HttpEntity<>(new Request(1,
                "{\"clientId\":" + clientId + ",\"accountId\":" + cardId + "}", RequestTypes.JSON));

        log.info("request = " + request.getBody());

        String url = "http://localhost:1703/getBalance/clients/" +
                clientId + "/cards/" + cardId;

        ResponseEntity<BalanceDTO> responseEntityStr = restTemplate.getForEntity(
                url, BalanceDTO.class, request);

        log.info("responseEntityStr = " + responseEntityStr.toString());

        return responseEntityStr.getBody();

    }

    public BalanceDTO withdrawMoneyFromTheCard(Long clientId, Long cardId, int money, int pin) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Request> request = new HttpEntity<>(new Request(1,
                "{\"clientId\":" + clientId + ",\"accountId\":" + cardId +
                        ",\"money\":" + money + "}", RequestTypes.JSON));

        log.info("request = " + request.getBody());

        String url = "http://localhost:1703/withdraw/clients/" +
                clientId + "/cards/" + cardId + "/"+ money;

        ResponseEntity<BalanceDTO> responseEntityStr = restTemplate.getForEntity(
                url, BalanceDTO.class, request);

        log.info("responseEntityStr = " + responseEntityStr.toString());

        return responseEntityStr.getBody();
    }

    public BalanceDTO depositMoneyFromTheCard(Long clientId, Long cardId, int money, int pin) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Request> request = new HttpEntity<>(new Request(1,
                "{\"clientId\":" + clientId + ",\"accountId\":" + cardId +
                        ",\"money\":" + money + "}", RequestTypes.JSON));

        log.info("request = " + request.getBody());

        String url = "http://localhost:1703/deposit/clients/" +
                clientId + "/cards/" + cardId + "/"+ money;

        ResponseEntity<BalanceDTO> responseEntityStr = restTemplate.getForEntity(
                url, BalanceDTO.class, request);

        log.info("responseEntityStr = " + responseEntityStr.toString());

        return responseEntityStr.getBody();
    }
}
