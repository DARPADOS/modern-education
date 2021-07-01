package pe.edu.upc.moderneducation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.moderneducation.model.entity.Teacher;
import pe.edu.upc.moderneducation.model.entity.User;
import pe.edu.upc.moderneducation.security.MyUserDetails;
import pe.edu.upc.moderneducation.service.crud.StudentService;
import pe.edu.upc.moderneducation.service.crud.TeacherService;
import pe.edu.upc.moderneducation.service.crud.UserService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("signup")
    public String registerView(Model model){
        try {
            User newUser = new User();
            model.addAttribute("newUser", newUser);
            return "/access/signup";
        } catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());        
        }
        return "/access/signup";
    }

    @PostMapping("signup")
    public String registerNewUser(@ModelAttribute("newUser") User newUser) {
        try {
            userService.registerNewUserAccount(newUser);
            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace();
			System.err.println(e.getMessage());   
        }
        return "redirect:/signup?error";
    }


    @GetMapping("student-register")
    public String registerNewStudent(Authentication auth){
        try {
            MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();
            studentService.createNewStudent(userSession.getUser());
            if(userSession.getUser().hasRoleTeacher()){
                userService.changeRole(userSession.getUser());
            }
            else{
                userService.AddRoleStudent(userSession.getUser());
            }
            return "redirect:/logout";
        } catch (Exception e) {
            e.printStackTrace();
			System.err.println(e.getMessage());
        }
        return "redirect:/success";
    }

    @GetMapping("teacher-register")
    public String registerTeacherView(Model model){
        Teacher newTeacher = new Teacher();
        model.addAttribute("newTeacher", newTeacher);
        return "/access/teacher-register";
    }

    @PostMapping("teacher-register")
    public String registerNewTeacher(Authentication auth, @ModelAttribute("newTeacher") Teacher newTeacher){
        try {
            MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();
            teacherService.createNewTeacher(userSession.getUser(), newTeacher);
            if(userSession.getUser().hasRoleStudent()){
                userService.changeRole(userSession.getUser());
            }
            else{
                userService.AddRoleTeacher(userSession.getUser());
            }
            return "redirect:/logout";
        } catch (Exception e) {
            e.printStackTrace();
			System.err.println(e.getMessage());
        }
        return "redirect:/success";
    }
}
