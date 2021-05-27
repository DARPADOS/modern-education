package pe.edu.upc.moderneducation.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.moderneducation.model.entity.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Integer> {

	
}
