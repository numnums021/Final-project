package ru.hj77.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hj77.server.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
