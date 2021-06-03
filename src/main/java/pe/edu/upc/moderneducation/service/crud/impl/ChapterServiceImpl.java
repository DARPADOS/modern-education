package pe.edu.upc.moderneducation.service.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.moderneducation.model.entity.Chapter;
import pe.edu.upc.moderneducation.model.repository.ChapterRepository;
import pe.edu.upc.moderneducation.service.crud.ChapterService;

@Service
public class ChapterServiceImpl implements ChapterService {

	@Autowired
	private ChapterRepository chapterRepository;
	
	@Override
	public JpaRepository<Chapter, Integer> getRepository() {
		return chapterRepository;
	}

}
