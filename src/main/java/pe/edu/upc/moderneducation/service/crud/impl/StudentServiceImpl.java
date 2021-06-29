package pe.edu.upc.moderneducation.service.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.moderneducation.model.entity.Student;
import pe.edu.upc.moderneducation.model.entity.User;
import pe.edu.upc.moderneducation.model.repository.StudentRepository;
import pe.edu.upc.moderneducation.service.crud.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository; 
	
	@Override
	public JpaRepository<Student, Integer> getRepository() {
		return studentRepository;
	}

	@Override
	public boolean exist(Integer userId) throws Exception {
		return findById(userId).isPresent();
	}

	@Override
	public Student createNewStudent(User user) throws Exception {
		Student student = new Student();

		student.setId(user.getId());
		student.setUser(user);
		return create(student);
	}
}
