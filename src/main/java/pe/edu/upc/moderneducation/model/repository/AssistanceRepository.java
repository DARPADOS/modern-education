package pe.edu.upc.moderneducation.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.moderneducation.model.entity.Assistance;


@Repository
public interface AssistanceRepository extends JpaRepository<Assistance, Integer>{
			
	@Query(value = "Select a.* from assistance a where a.student_id=?1 and a.videconference_id=?2", nativeQuery = true)
	Optional<Assistance>listaCheck(Integer student_id, Integer videconference_id);
}
