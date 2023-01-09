package com.university.coursemanagement.service;

import com.university.coursemanagement.dto.ResponseResult;
import com.university.coursemanagement.model.Instructor;

import java.util.List;


public interface InstructorService {

    ResponseResult<Instructor> createInstructor(Instructor instructor) throws Exception;
    
    ResponseResult<Instructor> readInstructorById(Integer instructorId);

    ResponseResult<List<Instructor>> readInstructors();
    
    ResponseResult<Instructor> updateInstructor(Integer instructorId, Instructor instructor);

    ResponseResult<Boolean> deleteInstructor(Integer instructorId);

    ResponseResult<Boolean> deleteAllInstructors();
}
