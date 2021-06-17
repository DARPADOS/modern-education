package pe.edu.upc.moderneducation.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.Teacher;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

	List<Course> findByNameContainingIgnoreCaseOrderByCreatedDateDesc(String name);
	List<Course> findByLanguageOrderByCreatedDateDesc(String language);

	@Query(value = "Select c.* from course c join detail_course_student dcs ON dcs.course_id = c.course_id where dcs.student_id = ?1 order by dcs.date_start DESC",
		nativeQuery = true)
	List<Course> findByStudent(Integer studentId);

	List<Course> findByTeacher(Teacher teacherId);

	@Query(value = "select * from course order by course_id desc limit 3", nativeQuery = true)
	List<Course> getTopCourses();
}
