package ru.hj77.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hj77.server.entities.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
