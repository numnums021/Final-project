package ru.hj77.server.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import ru.hj77.common.communication.Response;
import ru.hj77.common.communication.requests.RequestBasicOperations;
import ru.hj77.common.communication.requests.RequestCashTransactions;
import ru.hj77.server.exception.NoSuchDataException;
import ru.hj77.server.service.CardService;
import ru.hj77.server.service.SecurityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@AllArgsConstructor
@RestController
@RequestMapping("/card")
public class CardController {

    private final String AUTH_ERROR = "Ошибка аутентификации.";

    private CardService service;
    private SecurityService securityService;

    @PostMapping(value = "/balance")
    public Response getBalance(@RequestBody RequestBasicOperations request) {
       if (securityService.cardIsAuth(request.getCardId(), request.getPin()))
           return new Response(service.getBalance(request.getCardId()));
       else
           throw new NoSuchDataException(AUTH_ERROR);
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


    @RequestMapping(value="/logmeout", method = RequestMethod.POST)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "123123";
    }
}
