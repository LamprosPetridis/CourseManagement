package com.university.CourseManagement.controller;

import com.university.CourseManagement.dto.ResponseResult;
import com.university.CourseManagement.dto.ResponseStatus;
import com.university.CourseManagement.service.StudentService;
import com.university.CourseManagement.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * This is a Rest Controller which calls The Student Service (Dependency Injection)
 */

@RestController
@AllArgsConstructor
@RequestMapping("/student")
public class StudentController {


    @Autowired
    private StudentService studentService;

    /**
     * This endpoint calls a method of StudentService to Create a Student
     *
     * @param student
     * @return ResponseResult<Student>
     * @throws Exception
     */
    @PostMapping("/create")
    public ResponseResult<Student> create(@RequestBody Student student) throws Exception {
        return studentService.createStudent(student);
    }

    /**
     * This endpoint calls a method of StudentService to Get a Student
     *
     * @param id
     * @return ResponseResult<Student>
     */
    @GetMapping("/{id}")
    public ResponseResult<Student> findByVat(@PathVariable("id") int id) {
        return studentService.readStudentById(id);
    }

    /**
     * This endpoint calls a method of StudentService to Update a Student with this id
     *
     * @param id
     * @return ResponseResult<Owner>
     */
    @PutMapping("/{id}")
    public ResponseResult<Student> update(@PathVariable("id") int id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    /**
     * This endpoint call a method of StudentService to Delete the Student with this id
     *
     * @param id
     * @return ResponseResult<Owner>
     */
    @DeleteMapping("/{id}")
    public ResponseResult<Boolean> delete(@PathVariable("id") int id) {
        return studentService.deleteStudent(id);
    }


}
