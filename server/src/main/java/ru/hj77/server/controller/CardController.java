package ru.hj77.server.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hj77.common.dto.BalanceDTO;
import ru.hj77.common.dto.RequestDTO;
import ru.hj77.server.service.CardService;


@AllArgsConstructor
@RequestMapping("/card")
@RestController
public class CardController {

    private CardService service;

    @PostMapping(value = "/getBalance")
    public BalanceDTO getBalance(@RequestBody RequestDTO request) {
       return new BalanceDTO(service.getBalance(request.getClientId(), request.getCardId()));
    }

    @PostMapping("/withdraw/{money}")
    public BalanceDTO withdrawMoneyFromTheCard(@RequestBody RequestDTO request,
                                               @PathVariable double money){

        return new BalanceDTO(service.withdrawMoneyFromTheCard(request.getClientId(), request.getCardId(), money));
    }

    @PostMapping("/deposit/{money}")
    public BalanceDTO depositMoneyFromTheCard(@RequestBody RequestDTO request,
                                              @PathVariable double money){
        return new BalanceDTO(service.depositMoneyFromTheCard(request.getClientId(), request.getCardId(), money));
    }

}
