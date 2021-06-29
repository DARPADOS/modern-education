package pe.edu.upc.moderneducation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.moderneducation.service.crud.CourseService;
import pe.edu.upc.moderneducation.service.crud.LanguageService;
import pe.edu.upc.moderneducation.service.crud.TeacherService;
import pe.edu.upc.moderneducation.model.entity.Chapter;
import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.Language;
import pe.edu.upc.moderneducation.model.entity.Resource;
import pe.edu.upc.moderneducation.model.entity.Teacher;
import pe.edu.upc.moderneducation.model.entity.Video;
import pe.edu.upc.moderneducation.security.MyUserDetails;

@Controller
@RequestMapping("/courses")
@SessionAttributes("courseEdit")
public class CourseController {
    
    @Autowired
    private CourseService courseService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private LanguageService languageService;

    @GetMapping
	public String listCourses( Model model, @RequestParam(name="languageId",required=false) Integer languageId,
			Authentication auth) {
		try {

			MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();

			List<Course> courses=new ArrayList<Course>();
			Teacher teacher=teacherService.findById(userSession.getId()).get();
			if(languageId!=null){
				courses = courseService.findByLanguageIdAndTeacher(languageId, teacher);
			}
			else{
				courses = courseService.findByTeacher(teacher);
			}
			Course course = new Course();
			model.addAttribute("courseNew", course);
			List<Language> languages=languageService.getAll();
			model.addAttribute("languages", languages);
			model.addAttribute("courses", courses);
			return "course/courses";
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return "redirect:/";
	}

    @PostMapping("savenew")	// GET: /regions/savenew
	public String saveNewCourse(Model model, @ModelAttribute("courseNew") Course course, 
	RedirectAttributes redirectAttributes, @RequestParam("imgResource") MultipartFile img, Authentication auth) {
		try {
			MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();

			String limitName=new String();
			Teacher teacher=teacherService.findById(userSession.getId()).get();
			course.setTeacher(teacher);
			Course courseReturn = courseService.createCourse(course, img);
			model.addAttribute("course", courseReturn);
			if(course.getName().length()>5){
				limitName="limit name pa";
			}
			redirectAttributes.addFlashAttribute("limitName", limitName);
			return "redirect:/courses"; // Archivo Html
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";
	}

	@PostMapping("save/{id}")
	public String saveEdit(Model model, 
	@ModelAttribute("course") Course course, 
	@PathVariable("id") Integer id,
	@RequestParam("imageEdit") MultipartFile imageEdit) {
		try {
			Course getCourse=courseService.findById(id).get();
			/*getCourse.setName(course.getName());
			getCourse.setDescription(course.getDescription());
			getCourse.setLanguage(course.getLanguage());

			if(imageEdit.getOriginalFilename().isBlank()){
				getCourse.setMineture_image(getCourse.getMineture_image());
			}
			else{getCourse.setMineture_image(courseService.uploadImage(imageEdit));}*/
			courseService.updateCourse(getCourse, course, imageEdit);
			//model.addAttribute("course", courseReturn);
			return "redirect:/courses/"+getCourse.getId();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";
	}

	@GetMapping("{id}")
	public String findById(Model model, @PathVariable("id") Integer id, Authentication auth) {
		try {
			MyUserDetails userSession = (MyUserDetails) auth.getPrincipal();

			Optional<Course> optional = courseService.findById(id);
			if(optional.isPresent()) {
				Boolean isOwner=courseService.isOwner(userSession.getId(), id);
				if(isOwner){
					Chapter chapter=new Chapter();
					Video video=new Video();
					List<Language> languages=languageService.getAll();
					model.addAttribute("languages", languages);
					model.addAttribute("videoNew", video);
					model.addAttribute("chapterNew", chapter);
					//model.addAttribute("resourceNew", resource);
					model.addAttribute("course", optional.get());
					return "course/view";
				}else{
					return "search/notOwner";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";
	}

	@GetMapping("/delete/{id}")
	public String deleteById(@PathVariable("id") Integer id){
		try{
			courseService.deleteById(id);
			return "redirect:/courses";
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";
	}

	@GetMapping("/published/{id}")
	public String publishById(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
		try{
			String message="";
			boolean isPublished=courseService.changePublishedStatus(id);
			if(!isPublished){ message="The course has been withdrawn";}
			else{message="The course has been published successfully";}
			redirectAttributes.addFlashAttribute("published", message);
			return "redirect:/courses/"+ id;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";
	}

}
