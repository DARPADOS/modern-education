package pe.edu.upc.moderneducation.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.DetailCourseStudent;
import pe.edu.upc.moderneducation.model.entity.DetailCourseStudentId;

public interface DetailCourseStudentRepository extends JpaRepository<DetailCourseStudent, DetailCourseStudentId>{
    List<DetailCourseStudent> findByCourse(Course course);
}
