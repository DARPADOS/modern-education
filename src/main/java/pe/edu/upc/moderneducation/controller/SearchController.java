package pe.edu.upc.moderneducation.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.moderneducation.model.entity.Course;

import pe.edu.upc.moderneducation.model.entity.Videoconference;

import pe.edu.upc.moderneducation.security.MyUserDetails;

import pe.edu.upc.moderneducation.service.crud.CourseService;
import pe.edu.upc.moderneducation.service.crud.VideoconferenceService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private CourseService courseService;

    @Autowired
	private VideoconferenceService videoconferenceService;
    
    @GetMapping(value="results")
    public String searchCourse(Model model, @ModelAttribute("courseSearch") Course courseSearch, Authentication auth) {
        try {   
            MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();

            List<Course> results = courseService.findBySearchTerm(courseSearch.getName(), userSession.getId());
            String Term = courseSearch.getName();
            model.addAttribute("searchTerm", Term);
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
        return "redirect:/";
    }

    
    @GetMapping("SearchVideoconferences")
	public String searchVideoconferencesGet(Model model, @ModelAttribute("videoconferenceSearch") Videoconference videoconferenceSearch){
		System.out.println(videoconferenceSearch.getName());	
		try {
			
		List<Videoconference>videoconferenceFound=videoconferenceService.findByNameStartingWith(videoconferenceSearch.getName());
		model.addAttribute("videoconferenceFound", videoconferenceFound);
		model.addAttribute("videoconferenceSearch", videoconferenceSearch);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return "search/videoconferences_result";
	}
    
}
