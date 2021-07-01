package pe.edu.upc.moderneducation.service.crud;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.User;
import pe.edu.upc.moderneducation.security.MyUserDetails;

public interface UserService extends CrudService<User, Integer> {



List<User> StudentsByVideoconference(Integer id) throws Exception;


    User registerNewUserAccount(User user) throws Exception;
    // for searches
    List<User> findByLastNameandFirstName(String lastName,String firstName)throws Exception;
    List<User> findByLastNameStartingWithAndFirstNameStartingWith(String lastName,String firstName)throws Exception;

    void changeRole(User user)throws Exception;

    User AddRoleStudent(User user) throws Exception;
    User AddRoleTeacher(User user) throws Exception;

    User AddRolePremium(User user) throws Exception;
    
    User uploadImage(User user, MyUserDetails userSession ,MultipartFile userImage) throws Exception;
    
}
