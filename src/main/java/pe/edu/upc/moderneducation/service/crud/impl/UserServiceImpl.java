package pe.edu.upc.moderneducation.service.crud.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import pe.edu.upc.moderneducation.model.entity.Authority;
import pe.edu.upc.moderneducation.model.entity.User;
import pe.edu.upc.moderneducation.model.repository.AuthorityRepository;
import pe.edu.upc.moderneducation.model.repository.UserRepository;
import pe.edu.upc.moderneducation.model.types.UserAuthorities;
import pe.edu.upc.moderneducation.security.MyUserDetails;
import pe.edu.upc.moderneducation.service.crud.UserService;

@Service
public class UserServiceImpl implements UserService {
	public static String userImgDirectory=System.getProperty("user.dir")+"/src/main/resources/static/img/profile_image";
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthorityRepository authRepository;
	
	@Override
	public JpaRepository<User, Integer> getRepository() {
		return userRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> findByLastNameandFirstName(String lastName, String firstName) throws Exception {
		return userRepository.findByLastNameAndFirstName(lastName, firstName);
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> findByLastNameStartingWithAndFirstNameStartingWith(String lastName, String firstName)
			throws Exception {
		return userRepository.findByLastNameStartingWithAndFirstNameStartingWith(lastName, firstName);
	}
	@Transactional(readOnly=true)
	@Override
	public List<User> StudentsByVideoconference(Integer id) throws Exception {
		return userRepository.StudentsByVideoconference(id);
	}

	@Transactional
	@Override
	public User registerNewUserAccount(User user) throws Exception {
		if(usernameExist(user.getUsername())){
			throw new Exception("There is an account with that username: "
				+ user.getUsername());
		}
		else{
			if(emailExist(user.getEmail())){
				throw new Exception("There is an account with that email address: "
					+ user.getEmail());
			}
		}
		String encodedPass = passwordEncoder.encode(user.getPassword());

		User newUser = new User();

		newUser.setPassword(encodedPass);
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setEnable(true);
		newUser.setUsername(user.getUsername());
		newUser.setProfileImage("default_profile.jpg");
		
		return userRepository.save(newUser);
	}

	private boolean usernameExist(String username){
		return (userRepository.findByUsername(username).isPresent());
	}

	private boolean emailExist(String email){
		return (userRepository.findByEmail(email).isPresent());
	}

	@Override
	public void changeRole(User user) throws Exception {
		if(user.hasRoleStudent()){
			for (Authority auth : user.getAuthorities()) {
				if(auth.getAuthority().matches(UserAuthorities.ROLE_STUDENT.name())){
					auth.setAuthority(UserAuthorities.ROLE_TEACHER.name());
					authRepository.save(auth);
				}
			}
		}
		else{
			if(user.hasRoleTeacher()){
				for (Authority auth : user.getAuthorities()) {
					if(auth.getAuthority().matches(UserAuthorities.ROLE_TEACHER.name())){
						auth.setAuthority(UserAuthorities.ROLE_STUDENT.name());
						authRepository.save(auth);
					}
				}
			}
		}
	}

	@Override
	public User AddRoleStudent(User user) throws Exception {
		user.addAuthority(UserAuthorities.ROLE_STUDENT);
		return update(user);
	}

	@Override
	public User AddRoleTeacher(User user) throws Exception {
		user.addAuthority(UserAuthorities.ROLE_TEACHER);
		return update(user);
	}

	@Override
	public User AddRolePremium(User user) throws Exception {
		user.addAuthority(UserAuthorities.ROLE_PREMIUM);
		return update(user);
	}
	/*@Override
	public User uploadImage(User user, MultipartFile userImage) throws Exception {
		String originalName=userImage.getOriginalFilename();
		Path fileNameAndPath=Paths.get(userImgDirectory,originalName);
        try {
            Files.write(fileNameAndPath, userImage.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setProfileImage(originalName);
		return create(user);
	}*/
	@Override
	public User uploadImage(User user, MyUserDetails userSession ,MultipartFile userImage) throws Exception {
		String originalName=userImage.getOriginalFilename();
		Path fileNameAndPath=Paths.get(userImgDirectory,originalName);
        try {
            Files.write(fileNameAndPath, userImage.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        userSession.setProfileImage(originalName);
        user.setProfileImage(originalName);
		return create(user);
	}
}
