package by.webproject.repository;

import by.webproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
