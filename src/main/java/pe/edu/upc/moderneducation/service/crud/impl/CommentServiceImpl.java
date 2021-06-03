package pe.edu.upc.moderneducation.service.crud.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.moderneducation.model.entity.Comment;
import pe.edu.upc.moderneducation.model.entity.User;
import pe.edu.upc.moderneducation.model.entity.Video;
import pe.edu.upc.moderneducation.model.repository.CommentRepository;
import pe.edu.upc.moderneducation.service.crud.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRepository commentRepository;
		
	
	@Override
	public JpaRepository<Comment, Integer> getRepository() {
		return commentRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Comment> findByUser(User user) throws Exception {
		return commentRepository.findByUser(user);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Comment> findByVideo(Video video) throws Exception {
		return commentRepository.findByVideo(video);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Comment> findByMain(Comment main) throws Exception {
		return commentRepository.findByMain(main);
	}
	
}
