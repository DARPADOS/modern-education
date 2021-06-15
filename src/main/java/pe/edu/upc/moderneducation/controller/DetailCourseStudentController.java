package pe.edu.upc.moderneducation.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.Student;
import pe.edu.upc.moderneducation.service.crud.CourseService;
import pe.edu.upc.moderneducation.service.crud.DetailCourseStudentService;
import pe.edu.upc.moderneducation.service.crud.StudentService;

@Controller
@RequestMapping("/detail-course-student")
public class DetailCourseStudentController{
    @Autowired
	private StudentService studentService;

    @Autowired
    private DetailCourseStudentService detailCourseStudent;

    @Autowired
    private CourseService courseService;

    @GetMapping("/suscribe/{id}")
	public String registerStudentInCourse(@PathVariable("id") Integer id){
		try{

			detailCourseStudent.registerStudentInCourse(1, id);
			return "redirect:/courses/"+ id;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";
	}
}