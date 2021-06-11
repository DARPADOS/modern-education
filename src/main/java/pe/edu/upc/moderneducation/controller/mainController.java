package pe.edu.upc.moderneducation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("")
@SessionAttributes("")
public class mainController {
    @GetMapping(value="/")
    public String index() {
        return "index";
    }

    @GetMapping(value="/dash")
    public String dash() {
        return "fragments/dashboard1";
    }
    
}
