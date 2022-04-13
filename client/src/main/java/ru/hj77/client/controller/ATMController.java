package ru.hj77.client.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.hj77.client.exception.NoSuchDataException;
import ru.hj77.client.service.AtmsService;
import ru.hj77.common.communication.Response;
import ru.hj77.common.communication.security.AuthenticationRequest;
import ru.hj77.common.communication.security.AuthenticationResponse;

import java.util.Objects;


@Log
@AllArgsConstructor
@RestController
@RequestMapping("/atms")
public class ATMController {

    private final String EXC_INFO = "Проверьте правильность Вашего запроса.";

    private AtmsService service;

    @PostMapping("/balance")
    public Response getClientBalance(@RequestBody AuthenticationRequest request) {

        if ((Integer.parseInt(request.getUsername()) >= 0) && ((Integer.parseInt(request.getPassword()) >= 0))){
            return service.getClientBalance(request);
        }
        else
            throw new NoSuchDataException(EXC_INFO);

    }

    @GetMapping("/withdraw/cards/{cardId}/pin/{PIN}/money/{money}")
    public Response withdrawMoneyToCard(@PathVariable("cardId") Long cardId,
                                        @PathVariable("money") int money,
                                        @PathVariable("PIN") int pin) {
        if ((money > 0) && (pin >= 0))
            return service.withdrawMoneyToCard(
                    new AuthenticationRequest(cardId.toString(), String.valueOf(pin)), money);
        else
            throw new NoSuchDataException(EXC_INFO);
    }

    @GetMapping("/deposit/cards/{cardId}/pin/{PIN}/money/{money}")
    public Response depositMoneyToCard(@PathVariable("cardId") Long cardId,
                                       @PathVariable("money") int money,
                                       @PathVariable("PIN") int pin) {

        if ((money > 0) && (pin >= 0))
            return service.depositMoneyToCard(
                    new AuthenticationRequest(cardId.toString(), String.valueOf(pin)), money);
        else
            throw new NoSuchDataException(EXC_INFO);
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> auth(@RequestBody AuthenticationRequest request) {
        RestTemplate restTemplate = new RestTemplate();

        AuthenticationResponse response = restTemplate.postForObject(
                "http://localhost:1703/auth", request, AuthenticationResponse.class);

        return ResponseEntity.ok(Objects.requireNonNull(response));
    }

}
