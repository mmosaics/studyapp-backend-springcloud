package org.example.controller;

import org.example.pojo.Course;
import org.example.service.CourseService;
import org.example.util.GlobalResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping("/user-courses")
    @CrossOrigin
    public GlobalResult getCourseListByUserId(HttpServletRequest httpServletRequest) {

        Map<String, Object> map = courseService.fetchUserId(httpServletRequest);


        if(map == null) {
            return GlobalResult.errorMsg("Please check your login status");
        } else {
            List<Course> courses = courseService.getCourseByUserId((String) map.get("userId"));
            map.put("courses", courses);
            return GlobalResult.ok(map);
        }

    }

    @GetMapping("/info/{course_id}")
    @CrossOrigin
    public GlobalResult getCourseById(@PathVariable("course_id") String courseId) {
        return GlobalResult.ok(courseService.get(courseId));

    }


    @GetMapping("/all/{start}")
    @CrossOrigin
    public GlobalResult getAllCourse(@PathVariable("start") int start) {
        return GlobalResult.ok(courseService.getAllCourses(start));
    }


}
