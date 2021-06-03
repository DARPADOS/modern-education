package pe.edu.upc.moderneducation.service.crud;

import pe.edu.upc.moderneducation.model.entity.Video;

public interface VideoService extends CrudService<Video, Integer>{
    Video saveVideoByChapterId(Integer chapterId, Video entity) throws Exception;
}