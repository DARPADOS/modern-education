package pe.edu.upc.moderneducation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pe.edu.upc.moderneducation.model.entity.Student;
import pe.edu.upc.moderneducation.model.entity.User;
import pe.edu.upc.moderneducation.security.MyUserDetails;
import pe.edu.upc.moderneducation.service.crud.StudentService;
import pe.edu.upc.moderneducation.service.crud.UserService;

@Controller
@RequestMapping("/userS")
public class UserSController {
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String visualizar(Model model, Authentication auth) {
		try {
			String premium;
			MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();
			
			Student student=(Student)studentService.findById(userSession.getId()).get();
			
			model.addAttribute("student", student);
			if (student.getPremium()==true) {
				premium="Premium";
				
			} else {
				premium="Normal";
			}
			model.addAttribute("premium", premium);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "user/ProfileS";
	}
	
	@PostMapping("saveD")
	public String saveEdit1(Model model, @ModelAttribute("student") Student student, Authentication auth) {
		//System.out.println("COURSE ID:"+course.getId().toString());
		try {
			MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();
			
			Student getStudent=(Student)studentService.findById(userSession.getId()).get();
			getStudent.getUser().setFirstName(student.getUser().getFirstName());
			getStudent.getUser().setLastName(student.getUser().getLastName());
		
			studentService.update(getStudent);
			//model.addAttribute("course", courseReturn);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			//System.out.println("teacher: "+ getTeacher.getOccupation() );
		}
		return "redirect:/userS";
	}
	@PostMapping("save/C")
	public String saveEdit2(Model model, @ModelAttribute("student") Student student, Authentication auth, @RequestParam("imgResource") MultipartFile img) {
		//System.out.println("COURSE ID:"+course.getId().toString());
		try {
			
			MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();
			
			Student getStudent=(Student)studentService.findById(userSession.getId()).get();
			
			getStudent.getUser().setEmail(student.getUser().getEmail());
			User userReturn = userService.uploadImage(getStudent.getUser(), userSession, img);
			model.addAttribute("user", userReturn);
			studentService.update(getStudent);
			//model.addAttribute("course", courseReturn);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			//System.out.println("teacher: "+ getTeacher.getOccupation() );
		}
		return "redirect:/userS";
	}
}