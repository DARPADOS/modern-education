package pe.edu.upc.moderneducation.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.moderneducation.model.entity.Assistance;

@Repository
public interface AssistanceRepository extends JpaRepository<Assistance, Integer>{

}
