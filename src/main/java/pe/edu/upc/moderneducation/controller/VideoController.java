package pe.edu.upc.moderneducation.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import pe.edu.upc.moderneducation.model.entity.Chapter;
import pe.edu.upc.moderneducation.model.entity.Video;
import pe.edu.upc.moderneducation.service.crud.ChapterService;
import pe.edu.upc.moderneducation.service.crud.VideoService;

@Controller
@RequestMapping("/videos")
@SessionAttributes("videoEdit")
public class VideoController {

	@Autowired
	private VideoService videoService;

    @Autowired
    private ChapterService chapterService;

    @GetMapping("{id}")
	public String findById(Model model, @PathVariable("id") Integer id) {
		try {
			Optional<Chapter> optional = chapterService.findById(id);
			if(optional.isPresent()) {
				model.addAttribute("course", optional.get());
				return "course/view";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";
	}

	@PostMapping("savenew/{courseid}/{chapterid}")
	public String saveNewVideo(@ModelAttribute("videoNew") Video video, 
	@PathVariable("courseid") Integer courseid,
	@PathVariable("chapterid") Integer chapterid,
	@RequestParam("videoResource") MultipartFile videoResource) {
		try {
			videoService.newVideoByChapterId(chapterid, video,videoResource);
			return "redirect:/courses/"+courseid;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";
	}

	@GetMapping("/delete/{idcourse}/{idvideo}")
	public String deleteById(
	@PathVariable("idcourse") Integer idcourse, 
	@PathVariable("idvideo") Integer idvideo){
		try{
			videoService.deleteById(idvideo);
			return "redirect:/courses/"+idcourse;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/courses";
	}
}
