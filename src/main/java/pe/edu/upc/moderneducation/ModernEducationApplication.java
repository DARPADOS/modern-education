package pe.edu.upc.moderneducation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import pe.edu.upc.moderneducation.service.crud.impl.ResourceServiceImpl;
import java.io.File;
@SpringBootApplication
@ComponentScan({"pe.edu.upc.moderneducation","service.crud.impl"})
public class ModernEducationApplication {

	public static void main(String[] args) {
		new File(ResourceServiceImpl.uploadDirectory).mkdir();
		SpringApplication.run(ModernEducationApplication.class, args);
	}

}
