package org.example.dao;

import org.example.pojo.Chapter;
import org.example.pojo.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CourseDAO extends JpaRepository<Course, String> {

     Course findByCourseId(String courseId);

     //@Query(value = "select course_id, coursename, introduce, from t_cc_course", nativeQuery = true)
     @Query("select courseId, courseName, coursePic, teachername, createTime from Course")
     List<Course> findPart();




}
