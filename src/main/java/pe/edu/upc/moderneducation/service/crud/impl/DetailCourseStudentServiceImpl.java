package pe.edu.upc.moderneducation.service.crud.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.DetailCourseStudent;
import pe.edu.upc.moderneducation.model.entity.DetailCourseStudentId;
import pe.edu.upc.moderneducation.model.entity.Student;
import pe.edu.upc.moderneducation.model.repository.CourseRepository;
import pe.edu.upc.moderneducation.model.repository.DetailCourseStudentRepository;
import pe.edu.upc.moderneducation.model.repository.StudentRepository;
import pe.edu.upc.moderneducation.service.crud.DetailCourseStudentService;
import pe.edu.upc.moderneducation.util.DateTimeUtil;

@Service
public class DetailCourseStudentServiceImpl implements DetailCourseStudentService{

    @Autowired
    private DetailCourseStudentRepository detailCourseStudentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
	public JpaRepository<DetailCourseStudent, DetailCourseStudentId> getRepository() {
		return detailCourseStudentRepository;
	}

    @Override
    public DetailCourseStudent registerStudentInCourse(Integer idstudent, Integer id) throws Exception {
        DetailCourseStudent detail = new DetailCourseStudent();
        Course course=courseRepository.findById(id).get();
        Student student=studentRepository.findById(idstudent).get();
        detail.setCourse(course);
        detail.setStudent(student);
        detail.setDateStart(DateTimeUtil.getNow());

        return detailCourseStudentRepository.save(detail);
    }

    @Override
    public DetailCourseStudent addQualificationAndOpinion(Integer studentId, Integer courseId, DetailCourseStudent detail) throws Exception {
        
        DetailCourseStudentId id = new DetailCourseStudentId(courseId, studentId);

        DetailCourseStudent detailUpdate = findById(id).get();

        detailUpdate.setQualification(detail.getQualification());
        detailUpdate.setOpinion(detail.getOpinion());
        detailUpdate.setDateReview(DateTimeUtil.getNow());

        return update(detailUpdate);
    }

    @Override
    public boolean checkRegister(Integer studentId, Integer courseId) throws Exception {
        DetailCourseStudentId registerId = new DetailCourseStudentId(courseId, studentId);
        Optional<DetailCourseStudent> result = findById(registerId);
        return result.isPresent();
    }

    @Override
    public boolean checkQualified(Integer studentId, Integer courseId) throws Exception {
        DetailCourseStudentId registerId = new DetailCourseStudentId(courseId, studentId);
        DetailCourseStudent result = findById(registerId).get();
        return result.getQualification()!=null;
    }
    
}
