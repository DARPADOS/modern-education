package pe.edu.upc.moderneducation.service.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.Resource;
import pe.edu.upc.moderneducation.model.repository.CourseRepository;
import pe.edu.upc.moderneducation.model.repository.ResourceRepository;
import pe.edu.upc.moderneducation.service.crud.ResourceService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ResourceServiceImpl implements ResourceService{

    public static String uploadDirectory=System.getProperty("user.dir")+"/src/main/resources/static/img/uploads";

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public JpaRepository<Resource, Integer> getRepository() {
        return resourceRepository;
    }

    @Override
    public Resource saveResourceByCourseId(Integer courseId, MultipartFile file, String fileName) throws Exception{
        
        //ALMACENANDO EN DIRECTORIO
        //StringBuilder fileNames=new StringBuilder();
        String originalName=file.getOriginalFilename();
        String[] lst= originalName.split("\\.");
        String extension=lst[1];
        System.out.println(lst);
        Path fileNameAndPath=Paths.get(uploadDirectory,fileName+"."+extension);
        //fileNames.append(file.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, file.getBytes());
            //Files.move(fileNameAndPath, fileNameAndPath.resolveSibling(fileName+".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Resource resource= new Resource();
        //resource.setFiles(file.getBytes());
        resource.setName("Resource: " + fileName);
        resource.setType(file.getContentType());

        Course course = courseRepository.findById(courseId).get();
        resource.setCourse(course);

        return resourceRepository.save(resource);
    }
}
