package pe.edu.upc.moderneducation.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.moderneducation.model.entity.DetailCourseStudent;
import pe.edu.upc.moderneducation.model.entity.DetailCourseStudentId;

public interface DetailCourseStudentRepository extends JpaRepository<DetailCourseStudent, DetailCourseStudentId>{
    
}
