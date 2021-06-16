package pe.edu.upc.moderneducation.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.Teacher;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

	List<Course> findByNameContainingOrderByCreatedDateDesc(String name);
	List<Course> findByLanguageOrderByCreatedDateDesc(String language);

	List<Course> findByTeacher(Teacher teacher);

	@Query(value = "select * from course order by course_id desc limit 3", nativeQuery = true)
	List<Course> getTopCourses();
}
