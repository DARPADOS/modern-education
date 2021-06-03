package pe.edu.upc.moderneducation.service.crud;

import java.util.List;

import pe.edu.upc.moderneducation.model.entity.Teacher;

public interface TeacherService extends CrudService<Teacher, Integer>{
	List<Teacher> findByName(String name) throws Exception;
}
