package org.java.pizzeria.repo;

import org.java.pizzeria.pojo.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
