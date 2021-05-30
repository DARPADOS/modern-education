package pe.edu.upc.moderneducation.service.crud.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.moderneducation.model.entity.User;
import pe.edu.upc.moderneducation.model.repository.UserRepository;
import pe.edu.upc.moderneducation.service.crud.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public JpaRepository<User, Integer> getRepository() {
		// TODO Auto-generated method stub
		return userRepository;
	}
	@Transactional(readOnly = true)
	@Override
	public List<User> findByLastNameandFirstName(String lastName, String firstName) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findByLastNameandFirstName(lastName, firstName);
	}
	@Transactional(readOnly = true)
	@Override
	public List<User> findByLastNameStartingWithAndFirstNameStartingWith(String lastName, String firstName)
			throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findByLastNameStartingWithAndFirstNameStartingWith(lastName, firstName);
	}

}
