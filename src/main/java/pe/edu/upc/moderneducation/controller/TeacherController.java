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

import pe.edu.upc.moderneducation.model.entity.Teacher;
import pe.edu.upc.moderneducation.service.crud.TeacherService;

@Controller
@RequestMapping("/teachers")
@SessionAttributes("teacherEdit")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;

	@GetMapping
	public String list_teachers(Model model) {
		try {
			List<Teacher> teachers = teacherService.getAll();
			model.addAttribute("teachers", teachers);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

		return "teachers/lista_teacher";
	}
	
	@GetMapping("{id}")
	public String findById_teachers(Model model, @PathVariable("id") Integer id) {
		try {
			Optional<Teacher> optional = teacherService.findById(id);
			if(optional.isPresent()) {
				model.addAttribute("teacher", optional.get());
				return "teachert/view"; //Archivo Html
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/teachers"; //url
	}
	
	@GetMapping("{id}/edit")
	public String findById2_teachers(Model model, @PathVariable("id") Integer id) {
		try {
			Optional<Teacher> optional = teacherService.findById(id);
			if(optional.isPresent()) {
				model.addAttribute("teacherEdit", optional.get());
				return "teachers/edit";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/teachers"; //url
	}
	
	@PostMapping("save")
	public String saveEdit_teachers(Model model, @ModelAttribute("studentEdit") Teacher teacher) {
		try {
			Teacher teacherReturn = teacherService.update(teacher);
			model.addAttribute("teacher", teacherReturn);
			return "teachers/view"; // Archivo Html
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/teachers";
	}
	
	@GetMapping("new")
	public String newItem_teachers(Model model) {
		try {
			Teacher teacher = new Teacher();
			model.addAttribute("teacherNew", teacher);
			return "teachers/new";
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/teachers";	// url
	}
	
	@PostMapping("savenew")
	public String saveNew_teachers(Model model, @ModelAttribute("studentNew") Teacher teacher) {
		try {
			Teacher teacherReturn = teacherService.create(teacher);
			model.addAttribute("teacher", teacherReturn);
			return "teachers/view"; // Archivo Html
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/teachers";
	}
}
