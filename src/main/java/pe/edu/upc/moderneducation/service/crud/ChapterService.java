package pe.edu.upc.moderneducation.service.crud;

import pe.edu.upc.moderneducation.model.entity.Chapter;

public interface ChapterService extends CrudService<Chapter, Integer>{
    Chapter newChapterbyCourseId(Chapter chapter, Integer courseId);
}
