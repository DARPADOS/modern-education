package pe.edu.upc.moderneducation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.DetailCourseStudent;
import pe.edu.upc.moderneducation.model.entity.Teacher;
import pe.edu.upc.moderneducation.security.MyUserDetails;
import pe.edu.upc.moderneducation.service.crud.CourseService;
import pe.edu.upc.moderneducation.service.crud.DetailCourseStudentService;
import pe.edu.upc.moderneducation.service.crud.TeacherService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping("student")
public class StudentController {
    
    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;
    
    @Autowired
    private DetailCourseStudentService detailService;

    @GetMapping("courses")
    public String getCoursesByStudent(Model model, Authentication authentication) {
        try {
            MyUserDetails loggedUser = (MyUserDetails) authentication.getPrincipal();

            List<Course> courses = courseService.findByStudent(loggedUser.getUser().getId());
            model.addAttribute("myCourses", courses);
        } catch (Exception e) {
            e.printStackTrace();
			System.err.println(e.getMessage());
        }
        return "student/myCourses";
    }
	@GetMapping("course/{id}")
	public String findById(Model model, @PathVariable("id") Integer id,
            @RequestParam(name = "origin", required = false) String origin,
            Authentication auth) {
		try {
            MyUserDetails userSession = (MyUserDetails)auth.getPrincipal();

			Optional<Course> optional = courseService.findById(id);

			if(optional.isPresent()) {
                DetailCourseStudent newDetail = new DetailCourseStudent();
                boolean isRegisted = detailService.checkRegister(userSession.getId(), id);
                if(isRegisted){
                    boolean isQualified = detailService.checkQualified(userSession.getId(), id);
                    model.addAttribute("isQualified", isQualified);
                }
				model.addAttribute("course", optional.get());
                model.addAttribute("isRegisted", isRegisted);
                model.addAttribute("opinionNew", newDetail);
                model.addAttribute("origin", origin);
				return "student/courseDetail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/student/courses";
	}
	@GetMapping("courseT/{id}")
	public String findTeacherbyCourse(Model model, @PathVariable("id") Integer id) {
		try {
			
				Teacher teacher=teacherService.findById(id).get();
				model.addAttribute("teacher", teacher);
				return "user/ProfileTeacherView";
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:user/ProfileTeacherView";
	}
	

}
