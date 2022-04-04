package ru.hj77.server.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hj77.common.dto.BalanceDTO;
import ru.hj77.common.dto.ClientDTO;
import ru.hj77.server.service.ServerService;
import java.util.List;


@AllArgsConstructor
@RestController
public class CardController {

    private ServerService service;

    @GetMapping("/getBalance/clients/{clientId}/cards/{cardId}")
    public BalanceDTO getBalance(@PathVariable Long clientId,
                                 @PathVariable Long cardId) {
       return new BalanceDTO(service.getBalance(clientId, cardId));
    }

    @GetMapping("/withdraw/clients/{clientId}/cards/{cardId}/{money}")
    public BalanceDTO withdrawMoneyFromTheCard(@PathVariable Long clientId,
                                           @PathVariable Long cardId,
                                           @PathVariable double money){

        return new BalanceDTO(service.withdrawMoneyFromTheCard(clientId, cardId, money));
    }

    @GetMapping("/deposit/clients/{clientId}/cards/{cardId}/{money}")
    public BalanceDTO depositMoneyFromTheCard(@PathVariable Long clientId,
                                          @PathVariable Long cardId,
                                          @PathVariable double money){
        return new BalanceDTO(service.depositMoneyFromTheCard(clientId, cardId, money));
    }

}
