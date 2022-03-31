package ru.hj77.server.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/clients/{clientsId}")
    public ClientDTO getClient(@PathVariable Long clientsId){
        return service.getClient(clientsId) ;
    }

    @GetMapping("/getBalance/clients/{clientsId}/cards/{cardsId}")
    public Response getBalance(@PathVariable Long clientsId,
                             @PathVariable Long cardsId) {
       Response response = new Response(
               service.getClient(clientsId).
                       getCardsDTOList().stream().
                       filter(c -> c.getId_card() == cardsId).collect(Collectors.toList()).
                       get(0).getBalance()
       );

       log.info(response.toString());

       return response;
    }

    @GetMapping("/withdraw/clients/{clientsId}/cards/{cardsId}/{money}")
    public double withdrawMoneyFromTheCard(@PathVariable Long clientsId,
                                           @PathVariable Long cardsId,
                                           @PathVariable double money){

        return service.withdrawMoneyFromTheCard(clientsId, cardsId, money);
    }

    @GetMapping("/deposit/clients/{clientsId}/cards/{cardsId}/{money}")
    public double depositMoneyFromTheCard(@PathVariable Long clientsId,
                                          @PathVariable Long cardsId,
                                          @PathVariable double money){
        return service.depositMoneyFromTheCard(clientsId, cardsId, money);
    }

}
