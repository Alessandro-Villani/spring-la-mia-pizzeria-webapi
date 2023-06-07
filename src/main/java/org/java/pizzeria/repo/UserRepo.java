package org.java.pizzeria.repo;

import java.util.Optional;

import org.java.pizzeria.pojo.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Integer>{

	public Optional<User> findByUsername(String username);
	
}
