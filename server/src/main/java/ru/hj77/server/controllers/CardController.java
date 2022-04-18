package ru.hj77.server.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hj77.common.communication.requests.RequestCashTransactions;
import ru.hj77.common.communication.response.BasicResponse;
import ru.hj77.server.services.CardService;

import java.security.Principal;

@AllArgsConstructor
@RestController
@RequestMapping("/card")
public class CardController {

    private CardService service;

    @PostMapping(value = "/balance")
    public BasicResponse getBalance(Principal principal) {
        return new BasicResponse(service.getBalance(Long.parseLong(principal.getName())));
    }

    @PostMapping("/withdraw")
    public BasicResponse withdrawMoneyFromTheCard(@RequestBody RequestCashTransactions request, Principal principal){
        return new BasicResponse(service.withdrawMoneyFromTheCard(Long.parseLong(principal.getName()), request.getMoney()));
    }

    @PostMapping("/deposit")
    public BasicResponse depositMoneyFromTheCard(@RequestBody RequestCashTransactions request, Principal principal){
        return new BasicResponse(service.depositMoneyFromTheCard(Long.parseLong(principal.getName()), request.getMoney()));
    }

}
