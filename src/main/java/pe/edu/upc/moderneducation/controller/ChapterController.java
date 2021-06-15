package pe.edu.upc.moderneducation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.moderneducation.model.entity.Chapter;
import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.service.crud.ChapterService;
import pe.edu.upc.moderneducation.service.crud.CourseService;

@Controller
@RequestMapping("/chapters")
@SessionAttributes("chapterEdit")
public class ChapterController {
    
    @Autowired
    private ChapterService chapterService;

    @Autowired
    private CourseService courseService;

    @PostMapping("savenew/{courseid}")
	public String saveNewCourse(Model model, @ModelAttribute("chapterNew") Chapter chapter, @PathVariable("courseid") Integer courseid) {
		try {
			if(chapter.getId()==null){
				System.out.println("No hay id");
			}
			else{
				System.out.println("ID:"+chapter.getId());
			}
			chapterService.newChapterbyCourseId(chapter, courseid);
			return "redirect:/courses/"+courseid;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";
	}
    
}
