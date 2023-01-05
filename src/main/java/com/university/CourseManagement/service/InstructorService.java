package com.university.CourseManagement.service;

import com.university.CourseManagement.dto.ResponseResult;
import com.university.CourseManagement.model.Instructor;


public interface InstructorService {

    ResponseResult<Instructor> createInstructor(Instructor instructor) throws Exception;
    
    ResponseResult<Instructor> readInstructorById(int id);
    
    ResponseResult<Instructor> updateInstructor(int id, Instructor instructor);

    ResponseResult<Boolean> deleteInstructor(int id);

    ResponseResult<Boolean> assignCourse(int instructorId, int courseId);
    
}
