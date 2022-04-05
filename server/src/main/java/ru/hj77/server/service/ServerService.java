package ru.hj77.server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hj77.server.dto.ClientDTO;
import ru.hj77.server.exception.EntityNotFoundException;
import ru.hj77.server.repository.ClientRepository;
import ru.hj77.server.util.MappingUtils;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class ServerService {

    private ClientRepository clientCrudRepository;
    private MappingUtils mappingUtils;

    public ClientDTO getClient(Long id) {
        return mappingUtils.mapToClientDto(
                clientCrudRepository.findById(id)
                    .orElseThrow(EntityNotFoundException::new));
    }

    public List<ClientDTO> getAllClients() {
        return clientCrudRepository.findAll().stream().
                map(mappingUtils::mapToClientDto).collect(Collectors.toList());
    }
}
