package pe.edu.upc.moderneducation.service.crud;

import java.util.List;

import pe.edu.upc.moderneducation.model.entity.Course;

public interface CourseService extends CrudService<Course, Integer> {
	List<Course> findByName(String name) throws Exception;
	List<Course> findByLanguage(String language) throws Exception;
}
