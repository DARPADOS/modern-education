package pe.edu.upc.moderneducation.controller;

import java.util.List;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.Teacher;
import pe.edu.upc.moderneducation.model.entity.User;
import pe.edu.upc.moderneducation.model.entity.Videoconference;
import pe.edu.upc.moderneducation.security.MyUserDetails;
import pe.edu.upc.moderneducation.service.crud.TeacherService;
import pe.edu.upc.moderneducation.service.crud.UserService;
import pe.edu.upc.moderneducation.service.crud.VideoconferenceService;


@Controller
@RequestMapping("/videoconferences")

public class VideoconferenceController {

	@Autowired
	private VideoconferenceService videoconferenceService ;
	
	@Autowired
	private UserService userService ;
	
	@Autowired
	private TeacherService teacherService;
	
	@GetMapping
	public String listar(Model model, Authentication auth) {
		try {
			MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();
			
			Teacher teacher=teacherService.findById(userSession.getId()).get();
			
			List<Videoconference>videoconferences=videoconferenceService.findByTeacher(teacher);
			model.addAttribute("videoconferences", videoconferences);
			
			Videoconference videoconference=new Videoconference();
			model.addAttribute("videoconferenceNew", videoconference);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "videoconference/videoconferences";
	}
/*	@GetMapping("VideoconferenceStudents/{id}")
	public String FindStudentsByVideoconference(Model model, @PathVariable("id") Integer id) {
		try {
			
			List<User>videoconferenceStudents=userService.StudentsByVideoconference(id);
			model.addAttribute("Students", videoconferenceStudents);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "videoconference/view";
	}*/
	@PostMapping("savenew")
	public String saveNewVideoconference(Model model, @ModelAttribute("videoconferenceNew") Videoconference videoconference, Authentication auth) {
		try {
			MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();
			
			Teacher teacher=teacherService.findById(userSession.getId()).get();
			videoconference.setTeacher(teacher);
			
			Videoconference videoconferenceReturn=videoconferenceService.create(videoconference);
			model.addAttribute("videoconference", videoconferenceReturn);
			return "redirect:/videoconferences";
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/videoconferences"; //url
	}
	@GetMapping("{id}")		// GET: /videoconferences/{id}
	public String findById(Model model, @PathVariable("id") Integer id) {
		try {
			List<User>videoconferenceStudents=userService.StudentsByVideoconference(id);
			model.addAttribute("Students", videoconferenceStudents);
			
			Optional<Videoconference> optional = videoconferenceService.findById(id);
			if(optional.isPresent()) {
				model.addAttribute("videoconference", optional.get());
				return "videoconference/view";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/videoconferences"; //url
	}
	@PostMapping("save/{id}")
	public String saveEdit(Model model, @ModelAttribute("videoconference") Videoconference videoconference, @PathVariable("id") Integer id) {
		//System.out.println("COURSE ID:"+course.getId().toString());
		try {
			Videoconference getVideoconference=videoconferenceService.findById(id).get();
			getVideoconference.setName(videoconference.getName());
			getVideoconference.setDescription(videoconference.getDescription());
			getVideoconference.setDate(videoconference.getDate());
			getVideoconference.setHourStart(videoconference.getHourStart());
			getVideoconference.setHourEnd(videoconference.getHourEnd());
			getVideoconference.setMeetLink(videoconference.getMeetLink());
			videoconferenceService.update(getVideoconference);
			//model.addAttribute("course", courseReturn);
			return "redirect:/videoconferences/"+getVideoconference.getId();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/videoconferences";
	}
	
}