package pe.edu.upc.moderneducation.service.crud;


import pe.edu.upc.moderneducation.model.entity.Teacher;
import pe.edu.upc.moderneducation.model.entity.User;

public interface TeacherService extends CrudService<Teacher, Integer>{
    
    boolean exist(Integer userId) throws Exception;

    Teacher createNewTeacher(User user, Teacher newTeacher) throws Exception;
}
