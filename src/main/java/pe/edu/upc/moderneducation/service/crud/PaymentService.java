package pe.edu.upc.moderneducation.service.crud;

import java.util.List;

import pe.edu.upc.moderneducation.model.entity.Payment;
import pe.edu.upc.moderneducation.model.entity.Student;

public interface PaymentService extends CrudService<Payment, Integer>{
	List<Payment> findByStudent(Student student) throws Exception;
}
