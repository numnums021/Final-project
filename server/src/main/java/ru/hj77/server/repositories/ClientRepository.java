package ru.hj77.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hj77.server.entities.Client;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
