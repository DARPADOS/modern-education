package pe.edu.upc.moderneducation.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.edu.upc.moderneducation.model.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	List<User>findByLastNameAndFirstName(String lastName,String firstName);
	
	@Query("SELECT u FROM User u WHERE UPPER(u.lastName) LIKE CONCAT(UPPER(:lastName),'%') AND UPPER(u.firstName) LIKE CONCAT(UPPER(:firstName),'%')")
	List<User>findByLastNameStartingWithAndFirstNameStartingWith(String lastName,String firstName);

	
	@Query(value = "Select U.* from users U inner join student s on U.user_id=s.id inner join assistance a on a.student_id=S.id where a.videconference_id=?1", nativeQuery = true)
	List<User> StudentsByVideoconference(Integer id);


	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);

	
}
