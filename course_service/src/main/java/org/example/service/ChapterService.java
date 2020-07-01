package org.example.service;

import org.example.dao.ChapterDAO;
import org.example.pojo.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {

    private ChapterDAO chapterDAO;

    @Autowired
    public ChapterService(ChapterDAO chapterDAO) {
        this.chapterDAO = chapterDAO;
    }

    public List<Chapter> getChapterByCourseId(String courseId) {
        return chapterDAO.getChapterByCourseId(courseId);
    }

    public Chapter getChapterById(String chapterId) {
        return chapterDAO.getChapterByChapterId(chapterId);
    }

}
