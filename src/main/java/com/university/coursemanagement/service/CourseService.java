package com.university.coursemanagement.service;

import com.university.coursemanagement.dto.ResponseResult;
import com.university.coursemanagement.model.Course;

import java.time.Period;
import java.util.List;

public interface CourseService {

    ResponseResult<Course> createCourse(Course course) throws Exception;

    ResponseResult<Course> readCourse(Integer id);

    ResponseResult<List<Course>> readCourses();

    ResponseResult<Course> updateCourse(Integer id, Course course);

    ResponseResult<Boolean> deleteCourse(Integer id);

    ResponseResult<Boolean> deleteCourses();

    ResponseResult<Boolean> assignCourse(Integer courseId, Integer personId);

    ResponseResult<Boolean> assignCourses(Integer personId, List<Integer> courseIds);

    ResponseResult<List<Course>> viewCoursesPerPerson(Integer personId);



//    ResponseResult<Boolean> assignCourses(int personId, List<Integer> coursesId);

//    ResponseResult<Boolean> updateCourses(int personId, List<Integer> coursesId);

}
