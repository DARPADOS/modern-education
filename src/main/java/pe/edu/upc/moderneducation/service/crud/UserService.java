package pe.edu.upc.moderneducation.service.crud;

import java.util.List;



import pe.edu.upc.moderneducation.model.entity.User;

public interface UserService extends CrudService<User, Integer> {
List<User>findByLastNameandFirstName(String lastName,String firstName)throws Exception;
	
List<User>findByLastNameStartingWithAndFirstNameStartingWith(String lastName,String firstName)throws Exception;
}
