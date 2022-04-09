package ru.hj77.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hj77.server.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
