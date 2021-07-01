package pe.edu.upc.moderneducation.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.moderneducation.model.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    
}
