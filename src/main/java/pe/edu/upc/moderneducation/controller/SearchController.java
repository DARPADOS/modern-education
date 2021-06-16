package pe.edu.upc.moderneducation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.service.crud.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private CourseService courseService;

    @GetMapping(value="results")
    public String searchCourse(Model model, @ModelAttribute("courseSearch") Course courseSearch) {
        try {   
            List<Course> results = courseService.findByName(courseSearch.getName());
            String word = "\"" + courseSearch.getName() + "\"";
            model.addAttribute("word", word);
            if(results.size() > 0){
                model.addAttribute("results", results);
                return "search/result";
            }
            else{
                return "search/noFound";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
			System.err.println(e.getMessage());
        }
        return "index";
    }
    
}
