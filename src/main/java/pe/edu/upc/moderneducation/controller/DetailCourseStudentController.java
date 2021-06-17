package pe.edu.upc.moderneducation.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.moderneducation.model.entity.DetailCourseStudent;
import pe.edu.upc.moderneducation.service.crud.DetailCourseStudentService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/detail-course-student")
public class DetailCourseStudentController{

    @Autowired
    private DetailCourseStudentService detailService;

    @GetMapping("/suscribe/{id}")
	public String registerStudentInCourse(@PathVariable("id") Integer id){
		try{
			detailService.registerStudentInCourse(8, id);
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
			@ModelAttribute("opinionNew") DetailCourseStudent detail) {
		try {
			detailService.addQualificationAndOpinion(8, courseId, detail);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/student/course/"+ courseId;
	}
	
}