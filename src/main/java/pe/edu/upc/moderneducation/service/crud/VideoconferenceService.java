package pe.edu.upc.moderneducation.service.crud;

import java.util.List;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.User;
import pe.edu.upc.moderneducation.model.entity.Videoconference;

public interface VideoconferenceService extends CrudService<Videoconference, Integer> {
	List<Videoconference> findByName(String name)throws Exception;
	List<Videoconference> findByNameStartingWith(String name)throws Exception;
	
}
