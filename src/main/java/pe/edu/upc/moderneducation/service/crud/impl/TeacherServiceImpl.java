package pe.edu.upc.moderneducation.service.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.moderneducation.model.entity.Teacher;
import pe.edu.upc.moderneducation.model.entity.User;
import pe.edu.upc.moderneducation.model.repository.TeacherRepository;
import pe.edu.upc.moderneducation.service.crud.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherRepository teacherRepository; 
	
	@Override
	public JpaRepository<Teacher, Integer> getRepository() {
		return teacherRepository;
	}

	@Override
	public boolean exist(Integer userId) throws Exception {
		return findById(userId).isPresent();
	}

	@Override
	public Teacher createNewTeacher(User user, Teacher newTeacher) throws Exception {
		Teacher teacher = new Teacher();

		teacher.setCareer(newTeacher.getCareer());
		teacher.setOccupation(newTeacher.getCareer());
		teacher.setDescription(newTeacher.getDescription());

		teacher.setId(user.getId());
		teacher.setUser(user);

		return create(teacher);
	}
}
