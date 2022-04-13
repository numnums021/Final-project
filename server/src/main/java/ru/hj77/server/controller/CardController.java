package ru.hj77.server.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hj77.common.communication.response.BasicResponse;
import ru.hj77.common.communication.requests.BasicCashTransactions;
import ru.hj77.common.communication.requests.RequestCashTransactions;
import ru.hj77.server.service.CardService;

@AllArgsConstructor
@RestController
@RequestMapping("/card")
public class CardController {

    private CardService service;

    @PostMapping(value = "/balance")
    public BasicResponse getBalance(@RequestBody BasicCashTransactions request) {
        return new BasicResponse(service.getBalance(request.getCardId()));
    }

    @PostMapping("/withdraw")
    public BasicResponse withdrawMoneyFromTheCard(@RequestBody RequestCashTransactions request){
        return new BasicResponse(service.withdrawMoneyFromTheCard(request.getCardId(), request.getMoney()));
    }

    @PostMapping("/deposit")
    public BasicResponse depositMoneyFromTheCard(@RequestBody RequestCashTransactions request){
        return new BasicResponse(service.depositMoneyFromTheCard(request.getCardId(), request.getMoney()));
    }

}
