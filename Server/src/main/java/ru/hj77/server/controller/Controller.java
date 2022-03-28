package ru.hj77.server.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hj77.server.dto.ClientDTO;
import ru.hj77.server.service.ServerService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api")
public class Controller {

    private ServerService service;

    @GetMapping("/clients")
    public List<ClientDTO> showAllClients() {
        return service.getAllClients();
    }

    @GetMapping("/clients/{idClients}")
    public ClientDTO showClientsById(@PathVariable Long idClients){
        return service.getClient(idClients) ;
    }

    @GetMapping("/clients/getBalance/clients/{idClients}/cards/{idCards}")
    public double showClientBalance(@PathVariable Long idClients, @PathVariable Long idCards) {
        return service.showClientBalance(idClients, idCards);
    }

    @GetMapping("/withdraw/clients/{idClients}/cards/{idCards}/{money}")
    public double withdrawMoneyFromTheCard(@PathVariable Long idClients,
                                           @PathVariable Long idCards,
                                           @PathVariable double money){
        return service.withdrawMoneyFromTheCard(idClients, idCards, money);
    }

    @GetMapping("/deposit/clients/{idClients}/cards/{idCards}/{money}")
    public double depositMoneyFromTheCard(@PathVariable Long idClients,
                                          @PathVariable Long idCards,
                                          @PathVariable double money){
        return service.depositMoneyFromTheCard(idClients, idCards, money);
    }

}
