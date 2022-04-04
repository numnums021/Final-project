package ru.hj77.server.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.hj77.server.dto.ClientDTO;
import ru.hj77.server.service.ServerService;

import java.util.List;


@AllArgsConstructor
@RestController
public class ClientController {

    private ServerService service;

    @GetMapping("/clients")
    public List<ClientDTO> showAllClients() {
        return service.getAllClients();
    }

    @GetMapping("/clients/{clientId}")
    public ClientDTO getClient(@PathVariable Long clientId){
        return service.getClient(clientId);
    }
}
