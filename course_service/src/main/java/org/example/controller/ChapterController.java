package org.example.controller;


import org.example.pojo.Chapter;
import org.example.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/chapters")
public class ChapterController {

    private ChapterService chapterService;

    @Autowired
    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }


    @GetMapping("/{chapterId}")
    public Chapter getSpecifiedChapter(@PathVariable("chapterId") String chapterId) {

        return chapterService.getChapterById(chapterId);

    }


}
