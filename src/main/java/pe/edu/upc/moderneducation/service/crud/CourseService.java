package pe.edu.upc.moderneducation.service.crud;

import java.util.List;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.Teacher;

public interface CourseService extends CrudService<Course, Integer> {
	List<Course> findByName(String name) throws Exception;
	List<Course> findByLanguage(String language) throws Exception;

	List<Course> findByTeacher(Teacher teacher) throws Exception;

	Course publishCourse(Course course) throws Exception;
}