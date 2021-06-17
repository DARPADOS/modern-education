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

import pe.edu.upc.moderneducation.model.entity.Student;
import pe.edu.upc.moderneducation.service.crud.StudentService;

@Controller
@RequestMapping("/students")
@SessionAttributes("studentEdit")
public class StudentControllerV1 {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping
	public String list_students(Model model) {
		try {
			List<Student> students = studentService.getAll();
			model.addAttribute("students", students);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return "students/lista_student";
	}
	
	@GetMapping("{id}")
	public String findById_students(Model model, @PathVariable("id") Integer id) {
		try {
			Optional<Student> optional = studentService.findById(id);
			if(optional.isPresent()) {
				model.addAttribute("student", optional.get());
				return "student/view"; //Archivo Html
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/students"; //url
	}
	
	@GetMapping("{id}/edit")
	public String findById2_students(Model model, @PathVariable("id") Integer id) {
		try {
			Optional<Student> optional = studentService.findById(id);
			if(optional.isPresent()) {
				model.addAttribute("studentEdit", optional.get());
				return "students/edit";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/regions"; //url
	}
	
	@PostMapping("save")
	public String saveEdit_students(Model model, @ModelAttribute("studentEdit") Student student) {
		try {
			Student studentReturn = studentService.update(student);
			model.addAttribute("student", studentReturn);
			return "students/view"; // Archivo Html
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/students";
	}
	
	@GetMapping("new")
	public String newItem_students(Model model) {
		try {
			Student student = new Student();
			model.addAttribute("studentNew", student);
			return "students/new";
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/students";	// url
	}
	
	@PostMapping("savenew")
	public String saveNew_students(Model model, @ModelAttribute("studentNew") Student student) {
		try {
			Student studentReturn = studentService.create(student);
			model.addAttribute("student", studentReturn);
			return "students/view"; // Archivo Html
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/students";
	}
}
