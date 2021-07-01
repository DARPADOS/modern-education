package pe.edu.upc.moderneducation.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.moderneducation.model.entity.Teacher;
import pe.edu.upc.moderneducation.model.entity.Videoconference;


@Repository
public interface VideoconferenceRepository extends JpaRepository<Videoconference, Integer> {
	
	List<Videoconference> findByName(String name);
	List<Videoconference> findByNameStartingWith(String name);
	List<Videoconference> findByTeacher(Teacher teacher);
	
	@Query(value = "select ve.* from videoconference ve except select v.* from videoconference v \r\n"
			+ "		inner join assistance a on a.videconference_id=v.videoconference_id \r\n"
			+ "		inner join student s on s.id=a.student_id where s.id=?1", nativeQuery = true)
	List<Videoconference> findByStudentN(Integer studentId);
	
	@Query(value = "select v.* from videoconference v inner join "
			+ "assistance a on a.videconference_id=v.videoconference_id "
			+ "inner join student s on s.id=a.student_id where s.id=?1", nativeQuery = true)
	List<Videoconference> findByStudent(Integer studentId);
}
