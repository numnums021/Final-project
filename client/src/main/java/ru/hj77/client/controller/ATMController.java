package ru.hj77.client.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hj77.client.exception.NoSuchDataException;
import ru.hj77.client.service.AtmsService;

@AllArgsConstructor
@RestController
@RequestMapping("/atms")
public class ATMController {

    private final String EXC_INFO = "Проверьте правильность Ваших данных.";
    private AtmsService service;

    @GetMapping("/auth")
    public String auth(){
        return "Аутентификация прошла " + service.auth();
    }

    @GetMapping("/logout")
    public String logout() {
        service.logout();
        return "Вы вышли из системы!";
    }

    @GetMapping("/balance")
    public String getClientBalance() {
        return "Ваш баланс = " + service.getClientBalance().getBalance();
    }

    @GetMapping("/withdraw/{money}")
    public String withdrawMoneyToCard(@PathVariable("money") int money) {
        if (money > 0)
            return "Вы внесли денежные средства на карту. "
                + "Ваш баланс составляет: "
            + service.withdrawMoneyToCard(money).getBalance();
        else
            throw new NoSuchDataException(EXC_INFO);
    }

    @GetMapping("/deposit/{money}")
    public String depositMoneyToCard(@PathVariable("money") int money) {
        if (money > 0)
            return "Вы сняли денежные средства с карты. "
                    + "Ваш баланс составляет: "
                    + service.depositMoneyToCard(money).getBalance();
        else
            throw new NoSuchDataException(EXC_INFO);
    }
}
