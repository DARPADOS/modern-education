package pe.edu.upc.moderneducation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.moderneducation.model.entity.Payment;
import pe.edu.upc.moderneducation.service.crud.PaymentService;

@Controller
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@GetMapping
	public String listar_video(Model model) {
		try {
			List<Payment> payments=paymentService.getAll();
			model.addAttribute("payments", payments);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return "payments/lista_payment";
	}
	
	
	@GetMapping("new")           // GET: /regions/{id}/edit
	public String newItem(Model model) {
		try {
			Payment payment = new Payment();
			model.addAttribute("paymentNew", payment);
			return "payments/new";
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/payments";
	}
	
	@PostMapping("savenew")
	public String saveNew(Model model, @ModelAttribute("paymentNew") Payment payment) {
		try {
			Payment paymentReturn=paymentService.create(payment);
			model.addAttribute("payment", paymentReturn);
			return "payment/view";
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/payments";
	}
	
}
