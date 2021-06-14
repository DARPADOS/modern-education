package pe.edu.upc.moderneducation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
}
