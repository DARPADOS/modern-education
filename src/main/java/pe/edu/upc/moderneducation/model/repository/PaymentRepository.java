package pe.edu.upc.moderneducation.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.moderneducation.model.entity.Payment;
import pe.edu.upc.moderneducation.model.entity.Student;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	List<Payment> findByStudent(Student student);
}
