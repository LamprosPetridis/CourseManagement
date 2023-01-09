package com.university.coursemanagement.service;

import com.university.coursemanagement.dto.ResponseResult;
import com.university.coursemanagement.model.Course;
import com.university.coursemanagement.model.Student;

import java.util.List;


public interface StudentService {
    ResponseResult<Student> createStudent(Student student) ;

    ResponseResult<Student> readStudent(Integer studentId);

    ResponseResult<List<Student>> readStudents();

    ResponseResult<Student> updateStudent(Integer studentId, Student student);

    ResponseResult<Boolean> deleteStudent(Integer studentId);

    ResponseResult<Boolean> deleteAllStudents();


}
