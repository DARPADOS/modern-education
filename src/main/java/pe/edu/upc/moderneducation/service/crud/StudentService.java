package pe.edu.upc.moderneducation.service.crud;

import pe.edu.upc.moderneducation.model.entity.Student;
import pe.edu.upc.moderneducation.model.entity.User;

public interface StudentService extends CrudService<Student, Integer>{
    boolean exist(Integer userId) throws Exception;

    Student createNewStudent(User user) throws Exception;

    Student makeToPremium(Integer studentId) throws Exception;
}
