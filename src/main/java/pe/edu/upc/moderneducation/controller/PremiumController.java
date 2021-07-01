package pe.edu.upc.moderneducation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.moderneducation.security.MyUserDetails;
import pe.edu.upc.moderneducation.service.crud.StudentService;
import pe.edu.upc.moderneducation.service.crud.UserService;


@Controller
@RequestMapping("/premium")
public class PremiumController {
    
    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;


    @GetMapping
    public String premiumView(){
        try {
            return "premium/premium";
        } catch (Exception e) {
            e.printStackTrace();
			System.err.println(e.getMessage());
        }
        return "error";
    }

    @GetMapping("premium-access-success")
    public String premiumAccess(Authentication auth) {
        try {
            MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();
            
            userService.AddRolePremium(userSession.getUser());
            studentService.makeToPremium(userSession.getId());

            return "premium/success";
        } catch (Exception e) {
            e.getStackTrace();
            System.err.println(e.getMessage());
        }
        return "error";
    }
    

}
