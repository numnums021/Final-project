package ru.hj77.server.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import ru.hj77.common.OperationTypes;
import ru.hj77.common.Response;
import ru.hj77.server.dto.ClientDTO;
import ru.hj77.server.service.ServerService;
import java.util.List;
import java.util.stream.Collectors;


@Log
@AllArgsConstructor
@RestController
public class ServerController {

    private ServerService service;

    @GetMapping("/clients")
    public List<ClientDTO> showAllClients() {
        return service.getAllClients();
    }

    @GetMapping("/clients/{clientId}")
    public ClientDTO getClient(@PathVariable Long clientId){
        return service.getClient(clientId) ;
    }

    @GetMapping("/getBalance/clients/{clientId}/cards/{cardId}")
    public Response getBalance(@PathVariable Long clientId,
                             @PathVariable Long cardId) {
       return new Response(
               service.getClient(clientId).
                       getCardsDTOList().stream().
                       filter(c -> c.getId_card() == cardId).collect(Collectors.toList()).
                       get(0).getBalance()
       );
    }

    @GetMapping("/withdraw/clients/{clientId}/cards/{cardId}/{money}")
    public Response withdrawMoneyFromTheCard(@PathVariable Long clientId,
                                           @PathVariable Long cardId,
                                           @PathVariable double money){

        return new Response(service.moneyOperationsWithCardClient(clientId, cardId, money, OperationTypes.WITHDRAW));
    }

    @GetMapping("/deposit/clients/{clientId}/cards/{cardId}/{money}")
    public Response depositMoneyFromTheCard(@PathVariable Long clientId,
                                          @PathVariable Long cardId,
                                          @PathVariable double money){
        return new Response(service.moneyOperationsWithCardClient(clientId, cardId, money, OperationTypes.DEPOSIT));
    }

}
