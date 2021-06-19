package pe.edu.upc.moderneducation.service.crud;

import org.springframework.web.multipart.MultipartFile;

import pe.edu.upc.moderneducation.model.entity.Resource;

public interface ResourceService extends CrudService<Resource, Integer> {
    //Resource saveResourceByCourseId(Integer courseId, Resource resource, MultipartFile file) throws Exception;
    Resource saveResourceByCourseId(Integer courseId,MultipartFile file, String fileName) throws Exception;

}
