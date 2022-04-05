package ru.hj77.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hj77.server.entity.Card;
import ru.hj77.server.entity.Client;

public interface CardRepository extends JpaRepository<Card, Long> {
}
