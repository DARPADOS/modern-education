package pe.edu.upc.moderneducation.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.moderneducation.model.entity.DetailCourseStudent;
import pe.edu.upc.moderneducation.security.MyUserDetails;
import pe.edu.upc.moderneducation.service.crud.DetailCourseStudentService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/detail-course-student")
public class DetailCourseStudentController{

    @Autowired
    private DetailCourseStudentService detailService;

    @GetMapping("/suscribe/{id}")
	public String registerStudentInCourse(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes,
			Authentication auth){
		try{
			MyUserDetails userSession = (MyUserDetails)auth.getPrincipal();

			detailService.registerStudentInCourse(userSession.getId(), id);
			redirectAttributes.addFlashAttribute("success", "You have successfully registered");
			return "redirect:/student/course/"+ id;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";
	}
	@PostMapping("qualification/{id}")
	public String addQualification(Model model, @PathVariable("id") Integer courseId,
			@ModelAttribute("opinionNew") DetailCourseStudent detail,
			Authentication auth) {
		try {
			MyUserDetails userSession = (MyUserDetails)auth.getPrincipal();
			detailService.addQualificationAndOpinion(userSession.getId(), courseId, detail);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/student/course/"+ courseId;
	}
	
}