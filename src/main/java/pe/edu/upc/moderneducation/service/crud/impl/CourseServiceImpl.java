package pe.edu.upc.moderneducation.service.crud.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.Teacher;
import pe.edu.upc.moderneducation.model.repository.CourseRepository;
import pe.edu.upc.moderneducation.service.crud.CourseService;

@Service
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
		return courseRepository.findByLanguageOrderByCreatedDateDesc(language);
	}

	@Override
	public List<Course> findByTeacher(Teacher teacher) throws Exception {
		return courseRepository.findByTeacher(teacher);
	}

	@Override
	public Course create(Course entity) throws Exception {
		entity.setMineture_image("waifu.png");
		entity.setPublished(false);
		return CourseService.super.create(entity);
	}

	@Override
	public Course changePublishedStatus(Integer id) throws Exception {
		Course getCourse=courseRepository.findById(id).get();
		getCourse.setPublished(!getCourse.isPublished());
		return courseRepository.save(getCourse);
	}

	@Override
	public List<Course> getTopCourses() throws Exception {
		return courseRepository.getTopCourses();
	}
}
