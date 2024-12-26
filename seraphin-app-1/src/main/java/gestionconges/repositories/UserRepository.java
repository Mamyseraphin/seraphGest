package gestionconges.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestionconges.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {    
	User findByEmail (String email);
}
