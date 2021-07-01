package pe.edu.upc.moderneducation.service.crud;

import pe.edu.upc.moderneducation.model.entity.DetailCourseStudent;
import pe.edu.upc.moderneducation.model.entity.DetailCourseStudentId;

public interface DetailCourseStudentService extends CrudService<DetailCourseStudent, DetailCourseStudentId>{
    DetailCourseStudent registerStudentInCourse(Integer idstudent, Integer idcourse) throws Exception;
    DetailCourseStudent addQualificationAndOpinion(Integer studentId, Integer courseId, DetailCourseStudent detail) throws Exception;
    boolean checkRegister(Integer studentId, Integer CourseId) throws Exception;
    boolean checkQualified(Integer studentId, Integer CourseId) throws Exception;
}
