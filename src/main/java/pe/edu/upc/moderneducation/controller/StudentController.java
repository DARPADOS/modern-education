package pe.edu.upc.moderneducation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.DetailCourseStudent;
import pe.edu.upc.moderneducation.service.crud.CourseService;
import pe.edu.upc.moderneducation.service.crud.DetailCourseStudentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping("student")
public class StudentController {
    
    @Autowired
    private CourseService courseService;

    @Autowired
    private DetailCourseStudentService detailService;

    @GetMapping("courses")
    public String getMethodName(Model model) {
        try {
            List<Course> courses = courseService.findByStudent(10);
            model.addAttribute("myCourses", courses);
        } catch (Exception e) {
            e.printStackTrace();
			System.err.println(e.getMessage());
        }
        return "student/myCourses";
    }
    
	@GetMapping("course/{id}")
	public String findById(Model model, @PathVariable("id") Integer id) {
		try {
			Optional<Course> optional = courseService.findById(id);

			if(optional.isPresent()) {
                DetailCourseStudent newDetail = new DetailCourseStudent();
                boolean isRegisted = detailService.checkRegister(10, id);
                if(isRegisted){
                    boolean isQualified = detailService.checkQualified(10, id);
                    model.addAttribute("isQualified", isQualified);
                }
				model.addAttribute("course", optional.get());
                model.addAttribute("isRegisted", isRegisted);
                model.addAttribute("opinionNew", newDetail);
				return "student/courseDetail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/student/courses";
	}


}
