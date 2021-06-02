package pe.edu.upc.moderneducation.controller;

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

import pe.edu.upc.moderneducation.model.entity.User;
import pe.edu.upc.moderneducation.service.crud.UserService;


@Controller
@RequestMapping("")
@SessionAttributes("UserEdit")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("{id}/edit")		
	public String findById2(Model model, @PathVariable("id") Integer id) {
		try {
			Optional<User> optional = userService.findById(id);
			if(optional.isPresent()) {
				model.addAttribute("userEdit", optional.get());
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/"; //url
	}
	@PostMapping("save")
	public String saveEdit(Model model,@ModelAttribute("userEdit") User user) {
		try {
			User userReturn=userService.update(user);
			model.addAttribute("user", userReturn);
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
			User userO=new User();
			model.addAttribute("userNew", userO);
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/"; //url
	}
	@PostMapping("savenew")
	public String saveNew(Model model,@ModelAttribute("userNew") User user) {
		try {
			User userReturn=userService.create(user);
			model.addAttribute("user", userReturn);
			return "";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/"; //url
	}
}
