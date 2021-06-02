package pe.edu.upc.moderneducation.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.moderneducation.model.entity.Comment;
import pe.edu.upc.moderneducation.model.entity.User;
import pe.edu.upc.moderneducation.model.entity.Video;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	List<Comment> findByUser(User user);
	List<Comment> findByVideo(Video video);
	List<Comment> findByMain(Comment main);
}
