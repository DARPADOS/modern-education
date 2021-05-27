package pe.edu.upc.moderneducation.service.crud;

import pe.edu.upc.moderneducation.model.entity.Resource;

public interface ResourceService extends CrudService<Resource, Integer> {
    Resource saveResourceByCourseId(Integer courseId, Resource entity) throws Exception;
}
