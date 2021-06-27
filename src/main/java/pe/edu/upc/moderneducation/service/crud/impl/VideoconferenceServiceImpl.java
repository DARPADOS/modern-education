package pe.edu.upc.moderneducation.service.crud.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.moderneducation.model.entity.Teacher;
import pe.edu.upc.moderneducation.model.entity.Videoconference;
import pe.edu.upc.moderneducation.model.repository.VideoconferenceRepository;
import pe.edu.upc.moderneducation.service.crud.VideoconferenceService;

@Service
public class VideoconferenceServiceImpl implements VideoconferenceService {

	@Autowired
	private VideoconferenceRepository videoconferenceRepository;
	
	@Override
	public JpaRepository<Videoconference, Integer> getRepository() {
		// TODO Auto-generated method stub
		return videoconferenceRepository;
	}
	@Transactional(readOnly=true)
	@Override
	public List<Videoconference> findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return videoconferenceRepository.findByName(name);
	}
	@Transactional(readOnly=true)
	@Override
	public List<Videoconference> findByNameStartingWith(String name) throws Exception {
		// TODO Auto-generated method stub
		return videoconferenceRepository.findByNameStartingWith(name);
	}
	@Override
	public List<Videoconference> findByTeacher(Teacher teacher) throws Exception {
		// TODO Auto-generated method stub
		return videoconferenceRepository.findByTeacher(teacher);
	}
	

}
