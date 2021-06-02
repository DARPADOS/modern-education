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

import pe.edu.upc.moderneducation.model.entity.Comment;
import pe.edu.upc.moderneducation.service.crud.CommentService;

@Controller
@RequestMapping("/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	/*LISTAR*/
	
	@GetMapping
	public String list_comments(Model model) {
		try {
			List<Comment> comments = commentService.getAll();
			model.addAttribute("comments", comments);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return "comments/lista_comment";
	}
	
	
	/*VER MAS*/
	
	@GetMapping("{id}")
	public String findById(Model model, @PathVariable("id") Integer id) {
		try {
			Optional<Comment> optional=commentService.findById(id);
			if (optional.isPresent()) {
				model.addAttribute("comment", optional.get());
				return "comments/view"; 
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/comments";
	}
		
	
	/*EDITAR*/
	
	@GetMapping("{id}/edit")
	public String findById2(Model model, @PathVariable("id") Integer id) {
		try {
			Optional<Comment> optional=commentService.findById(id);
			if (optional.isPresent()) {
				model.addAttribute("comment", optional.get());
				return "comments/view"; 
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/comments";
	}
	
	@PostMapping("save")
	public String saveEdit(Model model, @ModelAttribute("commentEdit") Comment comment) {
		try {
			Comment commentReturn=commentService.update(comment);
			model.addAttribute("comment", commentReturn);
			return "comments/view";
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/comments";
	}
	
	
	/*INGRESAR*/
		
	@GetMapping("new")
	public String newItem(Model model) {
		try {
			Comment comment =new Comment();
			model.addAttribute("commentNew", comment);
			return "comments/new";
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/comments";
	}
	
	@PostMapping("savenew")
	public String saveNew(Model model, @ModelAttribute("commentNew") Comment comment) {
		try {
			Comment commentReturn=commentService.create(comment);
			model.addAttribute("comment", commentReturn);
			return "comments/view";
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/comments";
	}
}


