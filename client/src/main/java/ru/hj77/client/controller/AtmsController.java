package ru.hj77.client.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import ru.hj77.client.dto.BalanceDTO;
import ru.hj77.client.service.ClientService;


@AllArgsConstructor
@Log
@RestController
public class AtmsController {

    private ClientService clientService;

    @GetMapping("/clients/{clientId}/cards/{cardId}/pin/{PIN}")
    public BalanceDTO getClientBalance(
            @PathVariable("clientId") Long clientId,
            @PathVariable("cardId") Long cardId,
            @PathVariable("PIN") int pin) {

        return clientService.getClientBalance(clientId, cardId, pin);
    }

    @GetMapping("/withdraw/clients/{clientId}/cards/{cardId}/pin/{PIN}/{money}")
    public BalanceDTO withdrawMoneyFromTheCard(
            @PathVariable("clientId") Long clientId,
            @PathVariable("cardId") Long cardId,
            @PathVariable("money") int money,
            @PathVariable("PIN") int pin) {

        return clientService.withdrawMoneyFromTheCard(clientId, cardId, money, pin);
    }

    @GetMapping("/deposit/clients/{clientId}/cards/{cardId}/pin/{PIN}/{money}")
    public BalanceDTO depositMoneyFromTheCard(
            @PathVariable("clientId") Long clientId,
            @PathVariable("cardId") Long cardId,
            @PathVariable("money") int money,
            @PathVariable("PIN") int pin) {

        return clientService.depositMoneyFromTheCard(clientId, cardId, money, pin);
    }

}
