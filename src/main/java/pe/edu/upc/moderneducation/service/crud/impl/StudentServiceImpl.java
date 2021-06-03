package pe.edu.upc.moderneducation.service.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.moderneducation.model.entity.Student;
import pe.edu.upc.moderneducation.model.repository.StudentRepository;
import pe.edu.upc.moderneducation.service.crud.StudentService;

public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository; 
	
	@Override
	public JpaRepository<Student, Integer> getRepository() {
		return studentRepository;
	}
}
