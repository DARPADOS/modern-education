package pe.edu.upc.moderneducation.service.crud.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.moderneducation.model.entity.Teacher;
import pe.edu.upc.moderneducation.model.repository.TeacherRepository;
import pe.edu.upc.moderneducation.service.crud.TeacherService;

public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherRepository teacherRepository; 
	
	@Override
	public JpaRepository<Teacher, Integer> getRepository() {
		return teacherRepository;
	}

	@Override
	public List<Teacher> findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return teacherRepository.findByName(name);
	}

}
