package ru.hj77.server.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hj77.server.exception.NoSuchDataException;
import ru.hj77.server.dto.ClientDTO;
import ru.hj77.server.repositories.ClientRepository;
import ru.hj77.server.util.MappingUtils;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class ClientService {

    private ClientRepository clientCrudRepository;
    private MappingUtils mappingUtils;

    public ClientDTO getClient(Long id) {
        return mappingUtils.mapToClientDto(
                clientCrudRepository.findById(id)
                    .orElseThrow(NoSuchDataException::new));
    }

    public List<ClientDTO> getAllClients() {
        return clientCrudRepository.findAll().stream().
                map(mappingUtils::mapToClientDto).collect(Collectors.toList());
    }
}
