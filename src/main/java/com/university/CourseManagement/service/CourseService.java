package com.university.CourseManagement.service;

import com.university.CourseManagement.dto.ResponseResult;
import com.university.CourseManagement.model.Course;

public interface CourseService {

    ResponseResult<Course> createCourse(Course course) throws Exception;

    ResponseResult<Course> readCourseById(int id);

    ResponseResult<Course> updateCourse(int id, Course course);

    ResponseResult<Boolean> deleteCourse(int id);

//    ResponseResult<Boolean> assignCourse(int courseId, int id);

}
