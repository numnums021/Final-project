package ru.hj77.client.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.hj77.client.dto.BalanceDTO;
import ru.hj77.client.service.ClientService;
import ru.hj77.common.*;


@AllArgsConstructor
@Log
@RestController
public class AtmsController {

    private ClientService clientService;

    @GetMapping("/clients/{clientsId}/cards/{cardsId}/pin/{PIN}")
    public BalanceDTO getClientBalance(
            @PathVariable("clientsId") Long clientsId,
            @PathVariable("cardsId") Long cardsId,
            @PathVariable("PIN") int pin) {

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(
                "http://localhost:1703/getBalance/clients/{clientsId}/cards/{cardsId}",
                BalanceDTO.class, clientsId, cardsId);
    }
}
