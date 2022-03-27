package ru.hj77.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hj77.server.entity.Client;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
