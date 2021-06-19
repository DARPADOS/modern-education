package pe.edu.upc.moderneducation.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.upc.moderneducation.service.crud.ResourceService;

@Controller
@RequestMapping("/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

	@PostMapping("/uploadFile/{idcourse}")
	public String uploadFile(
		@RequestParam("file") MultipartFile docs,
		@RequestParam("fileName") String fileName,
		RedirectAttributes redirectAttr,
		@PathVariable("idcourse") Integer idcourse) {
		try {
			resourceService.saveResourceByCourseId(idcourse, docs, fileName);
			redirectAttr.addFlashAttribute("uploadMessage", "You successfully uploaded ");
			return "redirect:/courses/"+idcourse; 
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
