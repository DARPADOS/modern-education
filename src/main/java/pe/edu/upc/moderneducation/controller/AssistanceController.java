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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.moderneducation.model.entity.Assistance;
import pe.edu.upc.moderneducation.model.entity.Student;
import pe.edu.upc.moderneducation.model.entity.Teacher;
import pe.edu.upc.moderneducation.model.entity.User;
import pe.edu.upc.moderneducation.model.entity.Videoconference;
import pe.edu.upc.moderneducation.security.MyUserDetails;
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
	public String listar(Model model,Authentication auth) {
		try {
			MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();
			Videoconference videoconference=new Videoconference();
			model.addAttribute("videoconferenceNew", videoconference);
			
			Assistance assistance=new Assistance();
			model.addAttribute("asistenciaNew", assistance);
			
			
			List<Videoconference>videoconferences=videoconferenceService.findByStudentN(userSession.getId());
			model.addAttribute("videoconferences", videoconferences);
			//ponerlo aqui
			return "videoconference/VideoconferencesE";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/assistances"; 
	}
	@GetMapping("listaByStudent")
	public String listarByStudent(Model model,Authentication auth) {
		try {
			MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();	
			
			List<Videoconference>videoconferences=videoconferenceService.findByStudent(userSession.getId());
			model.addAttribute("videoconferencesM", videoconferences);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "videoconference/VideoconferenceES";
	}
	@GetMapping("detalle/{id}")		// GET: /videoconferences/{id}
	public String findById(Model model, @PathVariable("id") Integer id, Authentication auth) {
		try {
			
			Optional<Videoconference> optional = videoconferenceService.findById(id);
			
			MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();			
			 
			boolean isRegisted = assistanceService.checkRegister(userSession.getId(), id);
             
             
			if(optional.isPresent()) {
				model.addAttribute("videoconference", optional.get());
				model.addAttribute("isRegisted", isRegisted);
				return "videoconference/viewE";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/assistances"; //url
	}
	@GetMapping("{id}")
	public String saveNewAsistencia(Model model, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes ,@ModelAttribute("asistenciaNew") Assistance assistance, Authentication auth) {
		try {
			MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();
			Videoconference videoconference=videoconferenceService.findById(id).get();
			Student student=studentService.findById(userSession.getId()).get();
			assistance.setVideoconference(videoconference);
			assistance.setStudent(student);
			Assistance assistanceN=assistanceService.create(assistance);
			model.addAttribute("assistance", assistanceN);
			
			//assistanceService.checkRegister(userSession.getId(), id);
			return "redirect:/assistances";
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/assistances"; //url
	}
	/*@GetMapping("enrolled/{id}")
	public String enrolledById(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, Authentication auth){
		try{
			MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();
			String message="";
			boolean isEnrolled=assistanceService.checkRegister(userSession.getId(), id);
			if(!isEnrolled){ message="The videoconference has been withdrawn";}
			else{message="The videoconference has been published successfully";}
			redirectAttributes.addFlashAttribute("enrolled", message);
			return "redirect:/assistances";
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/assistances";
	}*/

}
