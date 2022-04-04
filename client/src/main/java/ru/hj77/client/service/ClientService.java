package ru.hj77.client.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hj77.client.dto.BalanceDTO;
import ru.hj77.common.Response;

@Service
public class ClientService {

    public BalanceDTO getClientBalance(Long clientId, Long cardId, int Pin) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:1703/getBalance/clients/" +
                clientId + "/cards/" + cardId;

        return restTemplate.getForObject(url, BalanceDTO.class);
    }

    public BalanceDTO withdrawMoneyFromTheCard(Long clientId, Long cardId, int money, int pin) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:1703/withdraw/clients/" +
                clientId + "/cards/" + cardId + "/"+ money;

        ResponseEntity<BalanceDTO> responseEntityStr = restTemplate.getForEntity(
                url, BalanceDTO.class);

        return responseEntityStr.getBody();
    }

    public BalanceDTO depositMoneyFromTheCard(Long clientId, Long cardId, int money, int pin) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:1703/deposit/clients/" +
                clientId + "/cards/" + cardId + "/"+ money;

        ResponseEntity<BalanceDTO> responseEntityStr = restTemplate.getForEntity(
                url, BalanceDTO.class);

        return responseEntityStr.getBody();
    }
}
