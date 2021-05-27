package pe.edu.upc.moderneducation.service.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.moderneducation.model.entity.Video;
import pe.edu.upc.moderneducation.model.repository.VideoRepository;
import pe.edu.upc.moderneducation.service.crud.VideoService;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    /*
    @Autowired
    private ChapterRepository chapterRepository;
    */

    @Override
    public JpaRepository<Video, Integer> getRepository() {
        return videoRepository;
    }

    @Override
    public Video saveVideoByChapterId(Integer chapterId, Video entity) throws Exception {

        /*
        Chapter chapter = chapterRepository.findById(chapterId);
        entity.setChapter(chapter);
        */
        return create(entity);
    }
}
