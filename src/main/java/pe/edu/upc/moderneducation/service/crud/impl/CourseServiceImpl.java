package pe.edu.upc.moderneducation.service.crud.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.Teacher;
import pe.edu.upc.moderneducation.model.repository.CourseRepository;
import pe.edu.upc.moderneducation.service.crud.CourseService;

public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public JpaRepository<Course, Integer> getRepository() {
		return courseRepository;
	}

	@Override
	public List<Course> findByName(String name) throws Exception {
		return courseRepository.findByNameOrderByCreatedDateDesc(name);
	}

	@Override
	public List<Course> findByLanguage(String language) throws Exception {
		return courseRepository.findByLanguageOrderByByCreatedDateDesc(language);
	}

	@Override
	public List<Course> findByTeacher(Teacher teacher) throws Exception {
		return courseRepository.findByTeacher(teacher);
	}

	@Override
	public Course publishCourse(Course course) throws Exception {
		course.setPublished(true);
		return courseRepository.save(course);
	}

}
