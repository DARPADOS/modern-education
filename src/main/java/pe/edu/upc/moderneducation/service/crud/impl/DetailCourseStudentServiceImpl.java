package pe.edu.upc.moderneducation.service.crud.impl;

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
