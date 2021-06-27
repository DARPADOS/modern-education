package pe.edu.upc.moderneducation.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.moderneducation.model.entity.Course;
import pe.edu.upc.moderneducation.model.entity.Teacher;
import pe.edu.upc.moderneducation.model.entity.User;
import pe.edu.upc.moderneducation.model.entity.Videoconference;


@Repository
public interface VideoconferenceRepository extends JpaRepository<Videoconference, Integer> {

	List<Videoconference> findByName(String name);
	List<Videoconference> findByNameStartingWith(String name);
	List<Videoconference> findByTeacher(Teacher teacher);
	
}
