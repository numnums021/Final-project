package ru.hj77.server.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import ru.hj77.common.communication.Response;
import ru.hj77.common.communication.requests.RequestBasicOperations;
import ru.hj77.common.communication.requests.RequestCashTransactions;
import ru.hj77.server.exception.NoSuchDataException;
import ru.hj77.server.service.CardService;
import ru.hj77.server.service.SecurityService;


@Log
@AllArgsConstructor
@RestController
@RequestMapping("/card")
public class CardController {

    private final String AUTH_ERROR = "Ошибка аутентификации.";

    private CardService service;
    private SecurityService securityService;

    @PostMapping(value = "/test")
    public String test(@RequestHeader String header) {
        if (header != null)
            log.info(String.valueOf("123"+header.contains("Barer ")));
        return header;
    }

    @PostMapping(value = "/balance")
    public Response getBalance(@RequestBody RequestBasicOperations request) {
        log.info(request.toString());
        return new Response(service.getBalance(request.getCardId()));
//       if (securityService.cardIsAuth(request.getCardId(), request.getPin()))
//           return new Response(service.getBalance(request.getCardId()));
//       else
//           throw new NoSuchDataException(AUTH_ERROR);
    }

    @PostMapping("/withdraw")
    public Response withdrawMoneyFromTheCard(@RequestBody RequestCashTransactions request){
        if (securityService.cardIsAuth(request.getCardId(), request.getPin()))
            return new Response(
                    service.withdrawMoneyFromTheCard(request.getCardId(), request.getMoney()));
        else
            throw new NoSuchDataException(AUTH_ERROR);
    }

    @PostMapping("/deposit")
    public Response depositMoneyFromTheCard(@RequestBody RequestCashTransactions request){
        if (securityService.cardIsAuth(request.getCardId(), request.getPin()))
            return new Response(service.depositMoneyFromTheCard(request.getCardId(), request.getMoney()));
        else
            throw new NoSuchDataException(AUTH_ERROR);
    }

}
