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

import pe.edu.upc.moderneducation.model.entity.Videoconference;
import pe.edu.upc.moderneducation.service.crud.VideoconferenceService;



@Controller
@RequestMapping("")
@SessionAttributes("videoconferenceEdit")
public class VideoconferenceController {

	@Autowired
	private VideoconferenceService videoconferenceService ;
	
	@GetMapping
	public String listar(Model model) {
		try {
			List<Videoconference>videoconferences=videoconferenceService.getAll();
			model.addAttribute("videoconferenceL", videoconferences);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "";
	}
	@GetMapping("{id}/edit")		
	public String findById2(Model model, @PathVariable("id") Integer id) {
		try {
			Optional<Videoconference> optional = videoconferenceService.findById(id);
			if(optional.isPresent()) {
				model.addAttribute("videoconferenceEdit", optional.get());
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/"; //url
	}
	@PostMapping("save")
	public String saveEdit(Model model,@ModelAttribute("videoconferenceEdit") Videoconference videoconference) {
		try {
			Videoconference videoconferenceReturn=videoconferenceService.update(videoconference);
			model.addAttribute("videoconference", videoconferenceReturn);
			return "";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/"; //url
	}
	@GetMapping("new")		
	public String newItem(Model model) {
		try {
			Videoconference videoconferenceO=new Videoconference();
			model.addAttribute("videoconferenceNew", videoconferenceO);
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/"; //url
	}
	@PostMapping("savenew")
	public String saveNew(Model model,@ModelAttribute("videoconferenceNew") Videoconference videoconference) {
		try {
			Videoconference videoconferenceReturn=videoconferenceService.create(videoconference);
			model.addAttribute("videoconference", videoconferenceReturn);
			return "";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/"; //url
	}
	
}
