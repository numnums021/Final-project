package ru.hj77.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hj77.server.entity.Client;

import java.util.Optional;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
