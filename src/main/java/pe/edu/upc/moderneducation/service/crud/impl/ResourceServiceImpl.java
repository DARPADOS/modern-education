package pe.edu.upc.moderneducation.service.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.Resource;
import pe.edu.upc.moderneducation.model.repository.CourseRepository;
import pe.edu.upc.moderneducation.model.repository.ResourceRepository;
import pe.edu.upc.moderneducation.service.crud.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService{

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public JpaRepository<Resource, Integer> getRepository() {
        return resourceRepository;
    }

    @Override
    public Resource saveResourceByCourseId(Integer courseId, Resource entity) throws Exception {
        Course course = courseRepository.findById(courseId).get();
        entity.setCourse(course);
        return resourceRepository.save(entity);
    }
}
