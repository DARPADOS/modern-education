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

import pe.edu.upc.moderneducation.model.entity.Teacher;
import pe.edu.upc.moderneducation.model.entity.User;

import pe.edu.upc.moderneducation.security.MyUserDetails;
import pe.edu.upc.moderneducation.service.crud.TeacherService;
import pe.edu.upc.moderneducation.service.crud.UserService;


@Controller
@RequestMapping("/userT")
public class UserTController {
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String visualizar(Model model, Authentication auth) {
		try {
			MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();
			
			Teacher teacher=(Teacher)teacherService.findById(userSession.getId()).get();
			
			model.addAttribute("teacher", teacher);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "user/ProfileT";
	}
	
	@PostMapping("saveD")
	public String saveEdit1(Model model, @ModelAttribute("teacher") Teacher teacher, Authentication auth) {
		//System.out.println("COURSE ID:"+course.getId().toString());
		try {
			MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();
			
			Teacher getTeacher=(Teacher)teacherService.findById(userSession.getId()).get();
			getTeacher.getUser().setFirstName(teacher.getUser().getFirstName());
			getTeacher.getUser().setLastName(teacher.getUser().getLastName());
			getTeacher.setOccupation(teacher.getOccupation());
			getTeacher.setCareer(teacher.getCareer());
			getTeacher.setDescription(teacher.getDescription());
			teacherService.update(getTeacher);
			//model.addAttribute("course", courseReturn);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			//System.out.println("teacher: "+ getTeacher.getOccupation() );
		}
		return "redirect:/userT";
	}
	@PostMapping("save/C")
	public String saveEdit2(Model model, @ModelAttribute("teacher") Teacher teacher, Authentication auth, @RequestParam("imgResource") MultipartFile img) {
		//System.out.println("COURSE ID:"+course.getId().toString());
		try {
			MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();
			
			Teacher getTeacher=(Teacher)teacherService.findById(userSession.getId()).get();
			getTeacher.getUser().setEmail(teacher.getUser().getEmail());
			getTeacher.getUser().setUsername(teacher.getUser().getUsername());
			User userReturn = userService.uploadImage(getTeacher.getUser(), userSession, img);
			model.addAttribute("user", userReturn);
			teacherService.update(getTeacher);
			
			
			
			//model.addAttribute("course", courseReturn);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			//System.out.println("teacher: "+ getTeacher.getOccupation() );
		}
		return "redirect:/userT";
	}
	
}
