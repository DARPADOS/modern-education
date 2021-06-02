package pe.edu.upc.moderneducation.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.moderneducation.model.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
