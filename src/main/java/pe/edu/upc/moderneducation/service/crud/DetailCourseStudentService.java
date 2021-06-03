package pe.edu.upc.moderneducation.service.crud;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.DetailCourseStudent;
import pe.edu.upc.moderneducation.model.entity.DetailCourseStudentId;
import pe.edu.upc.moderneducation.model.entity.Student;

public interface DetailCourseStudentService extends CrudService<DetailCourseStudent, DetailCourseStudentId>{
    DetailCourseStudent registerStudentInCourse(Student student, Course course) throws Exception;
}
