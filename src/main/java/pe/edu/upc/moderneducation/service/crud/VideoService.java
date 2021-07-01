package pe.edu.upc.moderneducation.service.crud;

import org.springframework.web.multipart.MultipartFile;

import pe.edu.upc.moderneducation.model.entity.Video;

public interface VideoService extends CrudService<Video, Integer>{
    Video newVideoByChapterId(Integer chapterId, Video entity, MultipartFile file) throws Exception;
}
