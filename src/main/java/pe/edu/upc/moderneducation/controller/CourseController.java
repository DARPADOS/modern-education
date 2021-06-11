package pe.edu.upc.moderneducation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.moderneducation.service.crud.CourseService;
import pe.edu.upc.moderneducation.service.crud.TeacherService;
import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.Teacher;

@Controller
@RequestMapping("/courses")
@SessionAttributes("courseEdit")
public class CourseController {
    
    @Autowired
    private CourseService courseService;

	@Autowired
	private TeacherService teacherService;

    @GetMapping
	public String listCourses( Model model ) {
		try {
			List<Course> courses = courseService.getAll();
			model.addAttribute("courses", courses);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return "course/courses";
	}

    @GetMapping("new")
	public String newCourse(Model model) {
		try {
			Course course = new Course();
			model.addAttribute("cNew", course);
			return "course/new";
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";	// url
	}

    @PostMapping("savenew")	// GET: /regions/savenew
	public String saveNewCourse(Model model, @ModelAttribute("cNew") Course course) {
		try {
			Teacher teacher=teacherService.findById(1).get();
			course.setTeacher(teacher);
			Course courseReturn = courseService.create(course);
			model.addAttribute("course", courseReturn);
			return "redirect:/courses"; // Archivo Html
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";
	}

	@GetMapping("{id}/edit")
	public String editCourse(Model model, @PathVariable("id") Integer id) {
		try {
			Optional<Course> optional = courseService.findById(id);
			if(optional.isPresent()) {
				model.addAttribute("courseEdit", optional.get());
				return "courses/edit";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";
	}

	@PostMapping("save")
	public String saveEdit(Model model, @ModelAttribute("courseEdit") Course course) {
		try {
			Course courseReturn = courseService.update(course);
			model.addAttribute("course", courseReturn);
			return "redirect:/courses"; 
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";
	}
}
