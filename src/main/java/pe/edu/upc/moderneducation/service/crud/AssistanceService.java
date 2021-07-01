package pe.edu.upc.moderneducation.service.crud;

import java.util.Optional;

import pe.edu.upc.moderneducation.model.entity.Assistance;


public interface AssistanceService extends CrudService<Assistance, Integer>{

	Optional<Assistance> listaCheck(Integer student_id, Integer videconference_id);
	
	public boolean checkRegister(Integer student_id, Integer videconference_id);
	
}
