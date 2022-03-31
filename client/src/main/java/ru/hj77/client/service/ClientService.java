package ru.hj77.client.service;



import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hj77.client.dto.BalanceDTO;
import ru.hj77.common.*;

@Service
public class ClientService {

    public BalanceDTO getClientBalance(Long clientId, Long cardId, int Pin) {

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Request> request = new HttpEntity<>(new Request(1,
                "{\"clientId\":" + clientId + ",\"accountId\":" + cardId + "}", RequestTypes.JSON));

        String url = "http://localhost:1703/getBalance/clients/" +
                clientId + "/cards/" + cardId;

        ResponseEntity<BalanceDTO> responseEntityStr = restTemplate.getForEntity(
                url, BalanceDTO.class, request);

        return responseEntityStr.getBody();

    }

    public BalanceDTO moneyOperationsWithCardClient(Long clientId, Long cardId, int money, int pin, OperationTypes operationTypes) {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Request> request = null;
        String url = null;

        switch (operationTypes) {
            case WITHDRAW:
                request = new HttpEntity<>(new Request(1,
                        "{\"clientId\":" + clientId + ",\"accountId\":" + cardId +
                                ",\"money\":" + money + "}", RequestTypes.JSON));

                url = "http://localhost:1703/deposit/clients/" +
                        clientId + "/cards/" + cardId + "/"+ money;
                break;

            case DEPOSIT:
                request = new HttpEntity<>(new Request(1,
                        "{\"clientId\":" + clientId + ",\"accountId\":" + cardId +
                                ",\"money\":" + money + "}", RequestTypes.JSON));
                url = "http://localhost:1703/withdraw/clients/" +
                        clientId + "/cards/" + cardId + "/"+ money;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operationTypes);
        }

        ResponseEntity<BalanceDTO> responseEntityStr = restTemplate.getForEntity(
                url, BalanceDTO.class, request);

        return responseEntityStr.getBody();
    }
}
