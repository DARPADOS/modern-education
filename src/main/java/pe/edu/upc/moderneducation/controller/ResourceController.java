package pe.edu.upc.moderneducation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.upc.moderneducation.model.entity.Resource;
import pe.edu.upc.moderneducation.service.crud.ResourceService;

@Controller
@RequestMapping("/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping("savenew/{courseid}")
	public String saveNewResource(
    @ModelAttribute("resourceNew") Resource resource, 
    @PathVariable("courseid") Integer courseid) {
		try {
			resourceService.saveResourceByCourseId(courseid, resource);
			return "redirect:/courses/"+courseid;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";
	}

	@GetMapping("/delete/{idcourse}/{idresource}")
	public String deleteById(
	@PathVariable("idcourse") Integer idcourse, 
	@PathVariable("idresource") Integer idresource){
		try{
			resourceService.deleteById(idresource);
			return "redirect:/courses/"+idcourse;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";
	}
}
