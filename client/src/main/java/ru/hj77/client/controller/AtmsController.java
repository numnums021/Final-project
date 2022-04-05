package ru.hj77.client.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hj77.client.service.AtmsService;
import ru.hj77.common.communication.Response;


@AllArgsConstructor
@RestController
@RequestMapping("/atms")
public class AtmsController {

    private AtmsService service;
    private final String URL_EXC = "Проверьте правильность Вашего запроса.";

    @GetMapping("/cards/{cardId}/pin/{PIN}")
    public Response getClientBalance(
            @PathVariable("cardId") Long cardId,
            @PathVariable("PIN") int pin) {

        return service.getClientBalance(cardId, pin);
    }

    @GetMapping("/withdraw/cards/{cardId}/pin/{PIN}/money/{money}")
    public Response withdrawMoneyToCard(
            @PathVariable("cardId") Long cardId,
            @PathVariable("money") int money,
            @PathVariable("PIN") int pin) {
        if ((money > 0) && (pin >= 0))
            return service.withdrawMoneyToCard(cardId, money, pin);
        else
            throw new RuntimeException(URL_EXC);
    }

    @GetMapping("/deposit/cards/{cardId}/pin/{PIN}/money/{money}")
    public Response depositMoneyToCard(
            @PathVariable("cardId") Long cardId,
            @PathVariable("money") int money,
            @PathVariable("PIN") int pin) {

        if ((money > 0) && (pin >= 0))
            return service.depositMoneyToCard(cardId, money, pin);
        else
            throw new RuntimeException(URL_EXC);
    }

}
