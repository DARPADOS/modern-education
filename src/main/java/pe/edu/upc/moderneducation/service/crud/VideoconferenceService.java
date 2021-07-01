package pe.edu.upc.moderneducation.service.crud;

import java.util.List;


import pe.edu.upc.moderneducation.model.entity.Teacher;
import pe.edu.upc.moderneducation.model.entity.Videoconference;

public interface VideoconferenceService extends CrudService<Videoconference, Integer> {
	List<Videoconference> findByName(String name)throws Exception;
	List<Videoconference> findByNameStartingWith(String name)throws Exception;
	List<Videoconference> findByTeacher(Teacher teacher)throws Exception;
	
	List<Videoconference> findByStudentN(Integer studentId)throws Exception;
	
	List<Videoconference> findByStudent(Integer studentId)throws Exception;;
}
