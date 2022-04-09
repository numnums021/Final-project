package ru.hj77.server.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hj77.server.dto.ClientDTO;
import ru.hj77.server.entity.Card;
import ru.hj77.server.service.SecurityService;
import ru.hj77.server.service.ServerService;

import java.security.Principal;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/info")
public class ClientController {

    private ServerService service;
    private SecurityService securityService;

    @GetMapping("/clients")
    public List<ClientDTO> showAllClients() {
        return service.getAllClients();
    }

    @GetMapping("/clients/{clientId}")
    public ClientDTO getClient(@PathVariable Long clientId){
        return service.getClient(clientId);
    }

    @GetMapping("/auth")
    public String pageForAuth(Principal principal) {
        Card card = securityService.findByCardId(principal.getName());
        return "Only for admins\nId card = " + card.getId_card()
                +"\nPin = " + card.getPin()
                +"\nBalance = " + card.getBalance();
    }
}
