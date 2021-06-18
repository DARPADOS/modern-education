package pe.edu.upc.moderneducation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    @GetMapping
    public String registerView(){
        try {
            return "/loginviews/login";
        } catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());        
        }
        return "/loginviews/login";
    }
}
