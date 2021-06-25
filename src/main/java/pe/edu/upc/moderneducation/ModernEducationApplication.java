package pe.edu.upc.moderneducation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import pe.edu.upc.moderneducation.service.crud.impl.CourseServiceImpl;
import pe.edu.upc.moderneducation.service.crud.impl.ResourceServiceImpl;
import pe.edu.upc.moderneducation.service.crud.impl.VideoServiceImpl;

import java.io.File;
@SpringBootApplication
@ComponentScan({"pe.edu.upc.moderneducation","service.crud.impl"})
public class ModernEducationApplication {

	public static void main(String[] args) {
		new File(ResourceServiceImpl.uploadDirectory).mkdir();
		new File(VideoServiceImpl.videoDirectory).mkdir();
		new File(CourseServiceImpl.courseImgDirectory).mkdir();
		SpringApplication.run(ModernEducationApplication.class, args);
	}

}
