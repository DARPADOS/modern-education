package pe.edu.upc.moderneducation.service.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.moderneducation.model.entity.Videoconference;
import pe.edu.upc.moderneducation.model.repository.VideoconferenceRepository;
import pe.edu.upc.moderneducation.service.crud.VideoconferenceService;

public class VideoconferenceServiceImpl implements VideoconferenceService {

	@Autowired
	private VideoconferenceRepository videoconferenceRepository;
	
	@Override
	public JpaRepository<Videoconference, Integer> getRepository() {
		// TODO Auto-generated method stub
		return videoconferenceRepository;
	}

}
