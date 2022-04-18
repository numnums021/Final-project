package ru.hj77.server.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hj77.server.dto.ClientDTO;
import ru.hj77.server.services.ClientService;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/info")
public class ClientController {

    private ClientService service;

    @GetMapping("/clients")
    public List<ClientDTO> showAllClients() {
        return service.getAllClients();
    }

    @GetMapping("/clients/{clientId}")
    public ClientDTO getClient(@PathVariable Long clientId){
        return service.getClient(clientId);
    }
}
