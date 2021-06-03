package pe.edu.upc.moderneducation.service.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.DetailCourseStudent;
import pe.edu.upc.moderneducation.model.entity.DetailCourseStudentId;
import pe.edu.upc.moderneducation.model.entity.Student;
import pe.edu.upc.moderneducation.model.repository.DetailCourseStudentRepository;
import pe.edu.upc.moderneducation.service.crud.DetailCourseStudentService;

public class DetailCourseStudentServiceImpl implements DetailCourseStudentService{

    @Autowired
    private DetailCourseStudentRepository detailCourseStudentRepository;

    @Override
	public JpaRepository<DetailCourseStudent, DetailCourseStudentId> getRepository() {
		return detailCourseStudentRepository;
	}

    @Override
    public DetailCourseStudent registerStudentInCourse(Student student, Course course) throws Exception {
        DetailCourseStudent detail = new DetailCourseStudent();
        
        detail.setCourse(course);
        detail.setStudent(student);

        return detailCourseStudentRepository.save(detail);
    }

    @Override
    public DetailCourseStudent addQualificationAndOpinion(Student student, Course course, Integer qualification,
            String opinion) throws Exception {
        
        DetailCourseStudentId id = new DetailCourseStudentId(course.getId(), student.getId());

        DetailCourseStudent detail = findById(id).get();

        detail.setQualification(qualification);
        detail.setOpinion(opinion);

        return update(detail);
    }
    
}
