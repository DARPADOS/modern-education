package pe.edu.upc.moderneducation.service.crud.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.edu.upc.moderneducation.model.entity.Chapter;
import pe.edu.upc.moderneducation.model.entity.Video;
import pe.edu.upc.moderneducation.model.repository.ChapterRepository;
import pe.edu.upc.moderneducation.model.repository.VideoRepository;
import pe.edu.upc.moderneducation.service.crud.VideoService;

@Service
public class VideoServiceImpl implements VideoService {

    public static String videoDirectory=System.getProperty("user.dir")+"/src/main/resources/static/videos";

    @Autowired
    private VideoRepository videoRepository;

    
    @Autowired
    private ChapterRepository chapterRepository;
    

    @Override
    public JpaRepository<Video, Integer> getRepository() {
        return videoRepository;
    }

    @Override
    public Video newVideoByChapterId(Integer chapterId, Video video, MultipartFile file) throws Exception {
        
        String originalName=file.getOriginalFilename();
        String[] lst= originalName.split("\\.");
        String extension=lst[lst.length-1];

        if(video.getName().isBlank()){
            if(file.getOriginalFilename().contains(extension)){
                video.setName(file.getOriginalFilename().replace(extension,""));
            }
        }

        Path fileNameAndPath=Paths.get(videoDirectory,video.getName()+"."+extension);
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Chapter chapter = chapterRepository.findById(chapterId).get();
        video.setChapter(chapter);
        video.setLink(video.getName()+"."+extension);
        return videoRepository.save(video);
    }
}
