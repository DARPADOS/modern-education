package pe.edu.upc.moderneducation.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.Teacher;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	List<Course> findByNameOrderByCreatedDateDesc(String name);
	List<Course> findByLanguageOrderByCreatedDateDesc(String language);

	List<Course> findByTeacher(Teacher teacher);
}
