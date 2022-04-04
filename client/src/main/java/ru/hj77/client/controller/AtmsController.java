package ru.hj77.client.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.hj77.client.service.AtmsService;
import ru.hj77.common.dto.BalanceDTO;


@AllArgsConstructor
@RequestMapping("/atms")
@RestController
public class AtmsController {

    private RestTemplate restTemplate;

    private AtmsService service;

    @GetMapping("/clients/{clientId}/cards/{cardId}/pin/{PIN}")
    public BalanceDTO getClientBalance(
            @PathVariable("clientId") Long clientId,
            @PathVariable("cardId") Long cardId,
            @PathVariable("PIN") int pin) {

        return service.getClientBalance(clientId, cardId, pin);
    }

    @GetMapping("/withdraw/clients/{clientId}/cards/{cardId}/pin/{PIN}/{money}")
    public BalanceDTO withdrawMoneyToCard(
            @PathVariable("clientId") Long clientId,
            @PathVariable("cardId") Long cardId,
            @PathVariable("money") int money,
            @PathVariable("PIN") int pin) {

        return service.withdrawMoneyToCard(clientId, cardId, money, pin);
    }

    @GetMapping("/deposit/clients/{clientId}/cards/{cardId}/pin/{PIN}/{money}")
    public BalanceDTO depositMoneyToCard(
            @PathVariable("clientId") Long clientId,
            @PathVariable("cardId") Long cardId,
            @PathVariable("money") int money,
            @PathVariable("PIN") int pin) {

        return service.depositMoneyToCard(clientId, cardId, money, pin);
    }

}
