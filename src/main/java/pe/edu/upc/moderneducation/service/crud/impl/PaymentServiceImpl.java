package pe.edu.upc.moderneducation.service.crud.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.moderneducation.model.entity.Payment;
import pe.edu.upc.moderneducation.model.entity.Student;
import pe.edu.upc.moderneducation.model.repository.PaymentRepository;
import pe.edu.upc.moderneducation.service.crud.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Override
	public JpaRepository<Payment, Integer> getRepository() {
		return paymentRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Payment> findByStudent(Student student) throws Exception {
		return paymentRepository.findByStudent(student);
	}
	
}
