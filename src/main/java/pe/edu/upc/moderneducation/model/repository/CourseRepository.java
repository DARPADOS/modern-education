package pe.edu.upc.moderneducation.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.moderneducation.model.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	List<Course> findByName(String name);
	List<Course> findByLanguage(String language);

	
}
