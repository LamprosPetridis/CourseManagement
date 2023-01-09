package com.university.coursemanagement.controller;

import com.university.coursemanagement.dto.ResponseResult;
import com.university.coursemanagement.model.Instructor;
import com.university.coursemanagement.model.Student;
import com.university.coursemanagement.service.InstructorService;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * This is a Rest Controller which calls The Instructor Service (Dependency Injection)
 */

@RestController
@AllArgsConstructor
@RequestMapping("/instructor")
public class InstructorController {


    @Autowired
    private InstructorService instructorService;

    /**
     * This endpoint calls a method of InstructorService to Create an Instructor
     *
     * @param instructor
     * @return ResponseResult<Instructor>
     * @throws Exception
     */
    @PostMapping("/create")
    public ResponseResult<Instructor> create(@RequestBody Instructor instructor) throws Exception {
        return instructorService.createInstructor(instructor);
    }

    /**
     * This endpoint calls a method of InstructorService to Get an Instructor
     *
     * @param instructorId
     * @return ResponseResult<Instructor>
     */
    @GetMapping("/{instructorId}")
    public ResponseResult<Instructor> findById(@PathVariable("id") Integer instructorId) {
        return instructorService.readInstructorById(instructorId);
    }

    /**
     * This endpoint calls a method of InstructorService to Get all Instructors
     *
     * @return ResponseResult<List<Instructors>>
     */
    @GetMapping("/")
    public ResponseResult<List<Instructor>> findStudents() {
        return instructorService.readInstructors();
    }

    /**
     * This endpoint calls a method of InstructorService to Update an Instructor with this id
     *
     * @param instructorId
     * @return ResponseResult<Instructor>
     */
    @PutMapping("/{instructorId}")
    public ResponseResult<Instructor> update(@PathVariable("instructorId") Integer instructorId, @RequestBody Instructor instructor) {
        return instructorService.updateInstructor(instructorId, instructor);
    }

    /**
     * This endpoint call a method of InstructorService to Delete the Instructor with this id
     *
     * @param instructorId
     * @return ResponseResult<Boolean>
     */
    @DeleteMapping("/{instructorId}")
    public ResponseResult<Boolean> delete(@PathVariable("instructorId") Integer instructorId) {
        return instructorService.deleteInstructor(instructorId);
    }

    /**
     * This endpoint call a method of InstructorService to Delete all instructors
     *
     * @return ResponseResult<Boolean>
     */
    @DeleteMapping("/")
    public ResponseResult<Boolean> delete() {
        return instructorService.deleteAllInstructors();
    }


}
