package pe.edu.upc.moderneducation.model.repository;

import org.springframework.stereotype.Repository;

import pe.edu.upc.moderneducation.model.entity.Video;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer>{
    
}
