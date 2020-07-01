package org.example.dao;

import org.example.pojo.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChapterDAO extends JpaRepository<Chapter, String> {


    List<Chapter> getChapterByCourseId(String courseId);

    Chapter getChapterByChapterId(String chapterId);

}
