package pe.edu.upc.moderneducation.controller;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.moderneducation.model.entity.Resource;
import pe.edu.upc.moderneducation.service.crud.ResourceService;
import pe.edu.upc.moderneducation.service.crud.impl.ResourceServiceImpl;
import pe.edu.upc.moderneducation.util.MediaTypeUtils;

@Controller
@RequestMapping("/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

	@Autowired
	private ServletContext servletContext;

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
	/*
	@GetMapping("/downloadFile/{linkresource}")
	@ResponseBody
	public FileSystemResource downloadFile(
	@PathVariable("linkresource") Integer linkresource ){
		try {
			Resource resource=resourceService.findById(linkresource).get();
			System.out.println(ResourceServiceImpl.uploadDirectory+ "/" +resource.getLink());
			return new FileSystemResource(new File(
				ResourceServiceImpl.uploadDirectory+ "/"+ resource.getLink()));
				
			//return "redirect:/courses/"+idcourse;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());	
		}
		return new FileSystemResource(new File(""));
	}
	*/
	
	@GetMapping("/downloadFile/{idresource}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("idresource") Integer idresource) throws Exception {

		Resource resource=resourceService.findById(idresource).get();

		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, resource.getLink());
        System.out.println("fileName: " + resource.getName());
        System.out.println("mediaType: " + mediaType);

		File file=new File(ResourceServiceImpl.uploadDirectory+"/"+resource.getLink());
        InputStreamResource streamResource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(streamResource);
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
