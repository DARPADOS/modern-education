package pe.edu.upc.moderneducation.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.edu.upc.moderneducation.model.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	List<User>findByLastNameAndFirstName(String lastName,String firstName);
	
	@Query("SELECT u FROM User u WHERE UPPER(u.lastName) LIKE CONCAT(UPPER(:lastName),'%') AND UPPER(u.firstName) LIKE CONCAT(UPPER(:firstName),'%')")
	List<User>findByLastNameStartingWithAndFirstNameStartingWith(String lastName,String firstName);
}
