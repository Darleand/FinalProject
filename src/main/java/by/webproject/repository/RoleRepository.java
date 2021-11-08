package by.webproject.repository;

import by.webproject.entity.Role;
import by.webproject.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.Set;

public interface RoleRepository extends CrudRepository<Role, String> {
    @Query("select r.name from Role r where r.name=:s")
    Role getRole(String s);
}
