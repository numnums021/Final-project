package ru.hj77.server.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hj77.common.communication.Response;
import ru.hj77.common.communication.Request;
import ru.hj77.server.exception.EntityNotFoundException;
import ru.hj77.server.service.CardService;
import ru.hj77.server.service.SecurityService;


@AllArgsConstructor
@RestController
@RequestMapping("/card")
public class CardController {

    private CardService service;
    private SecurityService securityService;
    private final String AUTH_ERROR = "Ошибка аутентификации.";

    @PostMapping(value = "/getBalance")
    public Response getBalance(@RequestBody Request request) {
       if (securityService.cardIsAuth(request.getCardId(), request.getPin()))
           return new Response(service.getBalance(request.getCardId()));
       else
           throw new EntityNotFoundException(AUTH_ERROR);
    }

    @PostMapping("/withdraw/{money}")
    public Response withdrawMoneyFromTheCard(@RequestBody Request request,
                                             @PathVariable double money){
        if (securityService.cardIsAuth(request.getCardId(), request.getPin()))
            return new Response(
                    service.withdrawMoneyFromTheCard(request.getCardId(), money));
        else
            throw new EntityNotFoundException(AUTH_ERROR);
    }

    @PostMapping("/deposit/{money}")
    public Response depositMoneyFromTheCard(@RequestBody Request request,
                                            @PathVariable double money){
        if (securityService.cardIsAuth(request.getCardId(), request.getPin()))
            return new Response(service.depositMoneyFromTheCard(request.getCardId(), money));
        else
            throw new EntityNotFoundException(AUTH_ERROR);
    }

}
