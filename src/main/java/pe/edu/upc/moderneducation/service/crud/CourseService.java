package pe.edu.upc.moderneducation.service.crud;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.Teacher;

public interface CourseService extends CrudService<Course, Integer> {
	
	List<Course> findBySearchTerm(String searchTerm, Integer studentId) throws Exception;
	
	List<Course> findByLanguage(String language) throws Exception;

	List<Course> findByTeacher(Teacher teacher) throws Exception;

	List<Course> getTopCourses() throws Exception;

	List<Course> findByStudent(Integer id) throws Exception;

	boolean changePublishedStatus(Integer id) throws Exception;

	Boolean isOwner(Integer idteacher, Integer idcourse);

	List<Course> findByLanguageIdAndTeacher(Integer languageId, Teacher teacher) throws Exception;

	String uploadImage(MultipartFile courseImage) throws Exception;

	Course createCourse(Course course, MultipartFile img) throws Exception;

	Course updateCourse(Course course, Course courseUpdated, MultipartFile img) throws Exception;
}