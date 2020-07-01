package org.example.service;

import com.hlr.constant.Constant;
import org.apache.dubbo.config.annotation.Reference;
import org.example.dao.CourseDAO;
import org.example.pojo.Chapter;
import org.example.pojo.Course;
import org.example.pojo.UserToCourseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CourseService {

    private CourseDAO courseDAO;

    private UserToCourseInfoService userToCourseInfoService;

    private ChapterService chapterService;

    @Reference
    AuthService authService;

    @Autowired
    public CourseService(CourseDAO courseDAO, UserToCourseInfoService userToCourseInfoService, ChapterService chapterService) {
        this.courseDAO = courseDAO;
        this.userToCourseInfoService = userToCourseInfoService;
        this.chapterService = chapterService;
    }

    public Map<String, Object> fetchUserId(HttpServletRequest httpServletRequest) {
        String sessionId = httpServletRequest.getHeader(Constant.AUTH_HEADER);
        Map<String, Object> userIdMap = authService.authUserInfo(sessionId);
        return userIdMap;
    }

    public Course get(String courseId) {
        Course course = courseDAO.findByCourseId(courseId);
        List<Chapter> chapters = chapterService.getChapterByCourseId(courseId);
        course.setChapters(chapters);
        return course;
    }

    public List<Course> getCourseByUserId(String userId) {

        List<UserToCourseInfo> userToCourseInfos = userToCourseInfoService.getUserToCourseInfoByUserId(userId);
        List<Course> courses = new ArrayList<>();
        for(UserToCourseInfo userToCourseInfo: userToCourseInfos) {
            Course course = get(userToCourseInfo.getCourseId());
            //避免不必要的空间浪费,具体的章节等点进具体课程后再加载
            course.setChapters(null);
            courses.add(course);
        }

        return courses;
    }

    public List<Course> getAllCourses(int start) {
        return courseDAO.findPart();
    }




}
