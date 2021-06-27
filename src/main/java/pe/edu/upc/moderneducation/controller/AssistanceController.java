package pe.edu.upc.moderneducation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.moderneducation.model.entity.Assistance;
import pe.edu.upc.moderneducation.model.entity.Student;
import pe.edu.upc.moderneducation.model.entity.Teacher;
import pe.edu.upc.moderneducation.model.entity.Videoconference;
import pe.edu.upc.moderneducation.service.crud.AssistanceService;
import pe.edu.upc.moderneducation.service.crud.StudentService;
import pe.edu.upc.moderneducation.service.crud.TeacherService;
import pe.edu.upc.moderneducation.service.crud.UserService;
import pe.edu.upc.moderneducation.service.crud.VideoconferenceService;


@Controller
@RequestMapping("/assistances")

public class AssistanceController {
	@Autowired
	private VideoconferenceService videoconferenceService ;
	
	//@Autowired
	//private UserService userService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private AssistanceService assistanceService;
	
	@GetMapping
	public String listar(Model model) {
		try {
			Videoconference videoconference=new Videoconference();
			model.addAttribute("videoconferenceNew", videoconference);
			
			Assistance assistance=new Assistance();
			model.addAttribute("asistenciaNew", assistance);
			
			
			List<Videoconference>videoconferences=videoconferenceService.getAll();
			model.addAttribute("videoconferences", videoconferences);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "videoconference/VideoconferencesE";
	}
	
	@GetMapping("{id}")
	public String saveNewAsistencia(Model model, @PathVariable("id") Integer id ,@ModelAttribute("asistenciaNew") Assistance assistance) {
		try {
			Videoconference videoconference=videoconferenceService.findById(id).get();
			Student student=studentService.findById(10).get();
			assistance.setVideoconference(videoconference);
			assistance.setStudent(student);
			Assistance assistanceN=assistanceService.create(assistance);
			model.addAttribute("assistance", assistanceN);

			return "videoconference/videoconferencesE";
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/videoconferencesE"; //url
	}
}
