package ru.hj77.server.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import ru.hj77.common.communication.Response;
import ru.hj77.common.communication.requests.BasicCashTransactions;
import ru.hj77.common.communication.requests.RequestCashTransactions;
import ru.hj77.server.service.CardService;


@Log
@AllArgsConstructor
@RestController
@RequestMapping("/card")
public class CardController {

    private CardService service;

    @PostMapping(value = "/balance")
    public Response getBalance(@RequestBody BasicCashTransactions request) {
        return new Response(service.getBalance(request.getCardId()));
    }

    @PostMapping("/withdraw")
    public Response withdrawMoneyFromTheCard(@RequestBody RequestCashTransactions request){
        return new Response(service.withdrawMoneyFromTheCard(request.getCardId(), request.getMoney()));
    }

    @PostMapping("/deposit")
    public Response depositMoneyFromTheCard(@RequestBody RequestCashTransactions request){
        return new Response(service.depositMoneyFromTheCard(request.getCardId(), request.getMoney()));
    }

}
