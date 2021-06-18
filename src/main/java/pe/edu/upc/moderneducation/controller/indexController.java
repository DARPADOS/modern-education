package pe.edu.upc.moderneducation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.service.crud.CourseService;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping("")
@ControllerAdvice
@SessionAttributes("")
public class indexController {

    @ModelAttribute
    public void addAttribute(Model model){
        Course course = new Course();
        model.addAttribute("courseSearch", course);
    }

    @Autowired
    CourseService courseService;

    @GetMapping(value="/")
    public String index(Model model) {
        try {
			List<Course> courses = courseService.getAll();
			model.addAttribute("topCourses", courses);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
        return "index";
    }
}
