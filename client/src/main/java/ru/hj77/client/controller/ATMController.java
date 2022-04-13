package ru.hj77.client.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hj77.client.exception.NoSuchDataException;
import ru.hj77.client.service.AtmsService;
import ru.hj77.common.security.AuthenticationRequest;

@AllArgsConstructor
@RestController
@RequestMapping("/atms")
public class ATMController {

    private final String EXC_INFO = "Проверьте правильность Ваших данных.";
    private AtmsService service;

    @GetMapping("/balance/cards/{cardId}/pin/{PIN}")
    public String getClientBalance(@PathVariable("cardId") Long cardId,
                                   @PathVariable("PIN") int pin) {

        if ((cardId >= 0) && (pin >= 0)){
            return "Ваш баланс = " + service.getClientBalance(
                    new AuthenticationRequest(cardId.toString(), String.valueOf(pin))).getBalance();
        }
        else
            throw new NoSuchDataException(EXC_INFO);
    }

    @GetMapping("/withdraw/cards/{cardId}/pin/{PIN}/money/{money}")
    public String withdrawMoneyToCard(@PathVariable("cardId") Long cardId,
                                      @PathVariable("money") int money,
                                      @PathVariable("PIN") int pin) {
        if ((money > 0) && (pin >= 0))
            return "Вы внесли денежные средства на карту. "
                + "Ваш баланс составляет: "
            + service.withdrawMoneyToCard(
                    new AuthenticationRequest(cardId.toString(), String.valueOf(pin)), money).getBalance();
        else
            throw new NoSuchDataException(EXC_INFO);
    }

    @GetMapping("/deposit/cards/{cardId}/pin/{PIN}/money/{money}")
    public String depositMoneyToCard(@PathVariable("cardId") Long cardId,
                                     @PathVariable("money") int money,
                                     @PathVariable("PIN") int pin) {

        if ((money > 0) && (pin >= 0))
            return "Вы внесли денежные средства на карту. "
                    + "Ваш баланс составляет: "
                    + service.depositMoneyToCard(
                    new AuthenticationRequest(cardId.toString(), String.valueOf(pin)), money).getBalance();
        else
            throw new NoSuchDataException(EXC_INFO);
    }
}
