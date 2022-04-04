package ru.hj77.client.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.hj77.common.dto.BalanceDTO;


@AllArgsConstructor
@RestController
public class AtmsController {

    private RestTemplate restTemplate;

    @GetMapping("/clients/{clientId}/cards/{cardId}/pin/{PIN}")
    public BalanceDTO getClientBalance(
            @PathVariable("clientId") Long clientId,
            @PathVariable("cardId") Long cardId,
            @PathVariable("PIN") int pin) {

        return restTemplate.getForObject(
                "http://localhost:1703/getBalance/clients/"+
                        clientId+"/cards/" + cardId,
                BalanceDTO.class);
    }

    @GetMapping("/withdraw/clients/{clientId}/cards/{cardId}/pin/{PIN}/{money}")
    public BalanceDTO withdrawMoneyFromTheCard(
            @PathVariable("clientId") Long clientId,
            @PathVariable("cardId") Long cardId,
            @PathVariable("money") int money,
            @PathVariable("PIN") int pin) {

        return restTemplate.getForObject(
                "http://localhost:1703/withdraw/clients/" + clientId +
                        "/cards/" + cardId + "/" + money,
                BalanceDTO.class);
    }

    @GetMapping("/deposit/clients/{clientId}/cards/{cardId}/pin/{PIN}/{money}")
    public BalanceDTO depositMoneyFromTheCard(
            @PathVariable("clientId") Long clientId,
            @PathVariable("cardId") Long cardId,
            @PathVariable("money") int money,
            @PathVariable("PIN") int pin) {

        return restTemplate.getForObject(
                "http://localhost:1703/deposit/clients/" + clientId +
                        "/cards/" + cardId + "/" + money,
                BalanceDTO.class);
    }

}
