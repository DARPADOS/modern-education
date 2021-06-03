package pe.edu.upc.moderneducation.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.moderneducation.model.entity.Videoconference;

public interface VideoconferenceRepository extends JpaRepository<Videoconference, Integer> {

}
