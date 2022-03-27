package ru.hj77.server.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/clients/getBalance/{idClients}/{idCards}")
    public double showClientBalance(@PathVariable Long idClients, @PathVariable Long idCards) {
        return service.showClientBalance(idClients, idCards);
    }
}
