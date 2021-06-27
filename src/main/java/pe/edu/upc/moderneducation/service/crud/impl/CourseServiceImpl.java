package pe.edu.upc.moderneducation.service.crud.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.DetailCourseStudent;
import pe.edu.upc.moderneducation.model.entity.Teacher;
import pe.edu.upc.moderneducation.model.repository.CourseRepository;
import pe.edu.upc.moderneducation.model.repository.TeacherRepository;
import pe.edu.upc.moderneducation.service.crud.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	public static String courseImgDirectory=System.getProperty("user.dir")+"/src/main/resources/static/img/courses";

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public JpaRepository<Course, Integer> getRepository() {
		return courseRepository;
	}

	@Override
	public List<Course> findBySearchTerm(String searchTerm, Integer studentId) throws Exception {
		return courseRepository.findByNameOrTeacherFirstNameOrTeacherLastName(searchTerm, studentId);
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
		//entity.setMineture_image("waifu.png");
		entity.setPublished(false);
		return CourseService.super.create(entity);
	}

	@Override
	public boolean changePublishedStatus(Integer id) throws Exception {
		Course getCourse=courseRepository.findById(id).get();
		getCourse.setPublished(!getCourse.isPublished());
		courseRepository.save(getCourse);
		return getCourse.isPublished();
	}

	@Override
	public List<Course> getTopCourses() throws Exception {
		return courseRepository.getTopCourses();
	}

	@Override
	public Boolean isOwner(Integer idteacher, Integer idcourse) {
		Boolean owner=false;
		Teacher teacher=teacherRepository.findById(idteacher).get();
		Course course=courseRepository.findById(idcourse).get();
		if(course.getTeacher().getId()==teacher.getId()){
			owner=true;
		}
		else{owner=false;}
		return owner;
	}
	
	@Override
	public Optional<Course> findById(Integer id) throws Exception {
		Optional<Course> course = courseRepository.findById(id);
		course.get().setCountRating(CountingRating(course.get().getDetailCourseStudent()));
		return course;
	}

	public List<Course> findByStudent(Integer id) throws Exception {
		return courseRepository.findByStudent(id);
	}

	private List<Integer> CountingRating(List<DetailCourseStudent> details) throws Exception {
		List<Integer> countRating = new ArrayList<Integer>();
		Integer countAll = 0;
		for (int i = 0; i < 5; i++) {
			countRating.add(0);
		}

		for (DetailCourseStudent detail : details) {
			if(detail.getQualification() != null && detail.getQualification() <=5){
				Integer rate = detail.getQualification();
				countRating.set(5-rate, countRating.get(5-rate) + 1);
				countAll++;
			}
		}
		for (int i = 0; i < 5; i++){
			float percent = ((float)countRating.get(i)/countAll)*100;
			countRating.set(i, Math.round(percent));
		}

		return countRating;
	}

	@Override
	public List<Course> findByLanguageIdAndTeacher(Integer languageId, Teacher teacher) throws Exception {
		return courseRepository.findByLanguageIdAndTeacher(languageId, teacher);
	}

	@Override
	public Course uploadImage(Course course, MultipartFile courseImage) throws Exception {
		String originalName=courseImage.getOriginalFilename();
		Path fileNameAndPath=Paths.get(courseImgDirectory,originalName);
        try {
            Files.write(fileNameAndPath, courseImage.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
		course.setMineture_image(originalName);
		return create(course);
	}

}
