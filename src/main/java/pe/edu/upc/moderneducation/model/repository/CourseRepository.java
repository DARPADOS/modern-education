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

	List<Course> findByPublishedOrderByCreatedDateDesc(boolean published);

	@Query(value = "select c.* from course c " +
		"join users u on c.teacher_id = u.user_id " +
		"full outer join detail_course_student dcs on c.course_id = dcs.course_id " +
		"where (UPPER(c.name) like UPPER(CONCAT('%', ?1, '%')) or " +
		"UPPER(u.first_name) like UPPER(CONCAT('%', ?1, '%')) or " +
		"UPPER(u.last_name) like UPPER(CONCAT('%', ?1, '%'))) and " +
		"c.published = 'true' and " +
		"c.course_id != ALL (select c2.course_id from course c2 " +
		"left join detail_course_student dcs2 on c2.course_id = dcs2.course_id "+
		"where dcs2.student_id = ?2) " +
		"group by c.course_id " +
		"order by c.created_date desc", nativeQuery = true) 
	List<Course> findByNameOrTeacherFirstNameOrTeacherLastName(String SearchTerm, Integer studentId);

	List<Course> findByLanguageIdAndTeacher(Integer languageId, Teacher teacher);
}
