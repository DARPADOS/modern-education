package pe.edu.upc.moderneducation.service.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.moderneducation.model.entity.Assistance;
import pe.edu.upc.moderneducation.model.repository.AssistanceRepository;
import pe.edu.upc.moderneducation.service.crud.AssistanceService;

@Service
public class AssistanceServiceImpl implements AssistanceService{

	@Autowired
	private AssistanceRepository assistanceRepository;
	
	@Override
	public JpaRepository<Assistance, Integer> getRepository() {
		// TODO Auto-generated method stub
		return assistanceRepository;
	}

	

	
	

}
