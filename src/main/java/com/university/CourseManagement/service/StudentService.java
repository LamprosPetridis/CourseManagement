package com.university.CourseManagement.service;

import com.university.CourseManagement.dto.ResponseResult;
import com.university.CourseManagement.model.Student;


public interface StudentService {

    ResponseResult<Student> createStudent(Student student) throws Exception;

    ResponseResult<Student> readStudentById(int id);

    ResponseResult<Student> updateStudent(int id, Student student);

    ResponseResult<Boolean> deleteStudent(int id);

}
