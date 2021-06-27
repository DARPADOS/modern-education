package pe.edu.upc.moderneducation.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.moderneducation.model.entity.Course;

import pe.edu.upc.moderneducation.model.entity.Videoconference;

import pe.edu.upc.moderneducation.security.MyUserDetails;

import pe.edu.upc.moderneducation.service.crud.CourseService;
import pe.edu.upc.moderneducation.service.crud.StudentService;
import pe.edu.upc.moderneducation.service.crud.TeacherService;
import pe.edu.upc.moderneducation.service.crud.UserService;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/")
@ControllerAdvice
public class indexController {

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    UserService userService;

    @ModelAttribute
    public void addAttribute(Model model){
        Course course = new Course();
        model.addAttribute("courseSearch", course);
    }
   /* @ModelAttribute
    public void addAttributeV(Model model){
    	 Videoconference  videoconferenceEd = new  Videoconference();
        model.addAttribute("videoconferenceE", videoconferenceEd);
    }*/
    @ModelAttribute
    public void addAttributeVideoconferences(Model model){
       Videoconference videoconference = new Videoconference();
        model.addAttribute("videoconferenceSearch", videoconference);
    }
    @Autowired
    CourseService courseService;

    @GetMapping
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

    @GetMapping("login")
    public String registerView(){
        return "access/login";
    }

    @GetMapping("access-denied")
    public String accessDenied() {
        return"access/access-denied";
    }

    @GetMapping("success")
    public String successLogin(Authentication authentication) {
        try {
            MyUserDetails userSession = (MyUserDetails) authentication.getPrincipal();
            if(!(studentService.exist(userSession.getId()) || teacherService.exist(userSession.getId()))){
                return "access/select-rol";
            }
            else{
                return "redirect:/";
            }
        } catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());        
        }
        return "redirect:/login";
    }
    
    @GetMapping("change-to-teacher")
    public String changeToTeacher(Authentication authentication){
        try {
            MyUserDetails userSession = (MyUserDetails) authentication.getPrincipal();
            if(!teacherService.exist(userSession.getId())){
                return "redirect:/teacher-register";
            }
            else {
                userService.changeRole(userSession.getUser());
                return "redirect:/logout";
            }
        } catch (Exception e) {
            e.printStackTrace();
			System.err.println(e.getMessage());    
        }
        return "error";
    }

    @GetMapping("change-to-student")
    public String changeToStudent(Authentication authentication){
        try {
            MyUserDetails userSession = (MyUserDetails) authentication.getPrincipal();
            if(!studentService.exist(userSession.getId())){
                return "redirect:/student-register";
            }
            else {
                userService.changeRole(userSession.getUser());
                return "redirect:/logout";
            }
        } catch (Exception e) {
            e.printStackTrace();
			System.err.println(e.getMessage());    
        }
        return "error";
    }
}
