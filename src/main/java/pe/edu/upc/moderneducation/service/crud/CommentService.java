package pe.edu.upc.moderneducation.service.crud;

import java.util.List;

import pe.edu.upc.moderneducation.model.entity.Comment;
import pe.edu.upc.moderneducation.model.entity.User;
import pe.edu.upc.moderneducation.model.entity.Video;

public interface CommentService extends CrudService<Comment, Integer>{
	List<Comment> findByUser(User user) throws Exception;
	List<Comment> findByVideo(Video video) throws Exception;
	List<Comment> findByMain(Comment main) throws Exception;
	Comment newCommentByVideo(Integer videoId, Comment entity);
}
