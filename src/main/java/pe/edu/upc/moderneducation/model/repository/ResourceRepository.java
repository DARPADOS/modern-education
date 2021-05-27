package pe.edu.upc.moderneducation.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.moderneducation.model.entity.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer>{
    
}
