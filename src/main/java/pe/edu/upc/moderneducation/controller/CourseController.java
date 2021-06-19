package pe.edu.upc.moderneducation.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.moderneducation.service.crud.CourseService;
import pe.edu.upc.moderneducation.service.crud.TeacherService;
import pe.edu.upc.moderneducation.model.entity.Chapter;
import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.Resource;
import pe.edu.upc.moderneducation.model.entity.Teacher;
import pe.edu.upc.moderneducation.model.entity.Video;

@Controller
@RequestMapping("/courses")
@SessionAttributes("courseEdit")
public class CourseController {
    
    @Autowired
    private CourseService courseService;

	@Autowired
	private TeacherService teacherService;

    @GetMapping
	public String listCourses( Model model ) {
		try {
			Teacher teacher=teacherService.findById(3).get();
			Course course = new Course();
			model.addAttribute("courseNew", course);
			List<Course> courses = courseService.findByTeacher(teacher);
			model.addAttribute("courses", courses);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return "course/courses";
	}

    @PostMapping("savenew")	// GET: /regions/savenew
	public String saveNewCourse(Model model, @ModelAttribute("courseNew") Course course) {
		try {
			Teacher teacher=teacherService.findById(1).get();
			course.setTeacher(teacher);
			Course courseReturn = courseService.create(course);
			model.addAttribute("course", courseReturn);
			return "redirect:/courses"; // Archivo Html
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";
	}

	@GetMapping("{id}/edit")
	public String editCourse(Model model, @PathVariable("id") Integer id) {
		try {
			Optional<Course> optional = courseService.findById(id);
			if(optional.isPresent()) {
				model.addAttribute("courseEdit", optional.get());
				return "courses/edit";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";
	}

	@PostMapping("save/{id}")
	public String saveEdit(Model model, @ModelAttribute("course") Course course, @PathVariable("id") Integer id) {
		//System.out.println("COURSE ID:"+course.getId().toString());
		try {
			Course getCourse=courseService.findById(id).get();
			getCourse.setName(course.getName());
			getCourse.setDescription(course.getDescription());
			getCourse.setLanguage(course.getLanguage());
			courseService.update(getCourse);
			//model.addAttribute("course", courseReturn);
			return "redirect:/courses/"+getCourse.getId();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";
	}

	@GetMapping("{id}")
	public String findById(Model model, @PathVariable("id") Integer id) {
		try {
			Optional<Course> optional = courseService.findById(id);
			if(optional.isPresent()) {
				Boolean isOwner=courseService.isOwner(3, id);
				if(isOwner){
					Chapter chapter=new Chapter();
					Video video=new Video();
					//Resource resource=new Resource();
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
