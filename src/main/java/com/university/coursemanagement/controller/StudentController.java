package com.university.coursemanagement.controller;

import com.university.coursemanagement.dto.ResponseResult;
import com.university.coursemanagement.service.ExcelGenerator;
import com.university.coursemanagement.service.StudentService;
import com.university.coursemanagement.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


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
    public ResponseResult<Student> create(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    /**
     * This endpoint calls a method of StudentService to Get a Student
     *
     * @param studentId
     * @return ResponseResult<Student>
     */
    @GetMapping("/{studentId}")
    public ResponseResult<Student> findStudent(@PathVariable("studentId") Integer studentId) {
        return studentService.readStudent(studentId);
    }

    /**
     * This endpoint calls a method of StudentService to Get all Students
     *
     * @return ResponseResult<List<Student>>
     */
    @GetMapping("/")
    public ResponseResult<List<Student>> findStudents() {
        return studentService.readStudents();
    }


    /**
     * This endpoint calls a method of StudentService to Update a Student with this id
     *
     * @param studentId
     * @return ResponseResult<Student>
     */
    @PutMapping("/{studentId}")
    public ResponseResult<Student> update(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
        return studentService.updateStudent(studentId, student);
    }

    /**
     * This endpoint call a method of StudentService to Delete the Student with this id
     *
     * @param studentId
     * @return ResponseResult<Boolean>
     */
    @DeleteMapping("/{studentId}")
    public ResponseResult<Boolean> delete(@PathVariable("studentId") Integer studentId) {
        return studentService.deleteStudent(studentId);
    }

    /**
     * This endpoint call a method of StudentService to Delete all students
     *
     * @return ResponseResult<Boolean>
     */
    @DeleteMapping("/")
    public ResponseResult<Boolean> delete() {
        return studentService.deleteAllStudents();
    }


    @GetMapping("/export-to-excel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=student" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List <Student> listOfStudents = studentService.readStudents().getData();
        ExcelGenerator generator = new ExcelGenerator(listOfStudents);
        generator.generateExcelFile(response);
    }
}


