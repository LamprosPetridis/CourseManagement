package com.university.CourseManagement.controller;

import com.university.CourseManagement.dto.ResponseResult;
import com.university.CourseManagement.model.Instructor;
import com.university.CourseManagement.service.InstructorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
     * @param id
     * @return ResponseResult<Instructor>
     */
    @GetMapping("/{id}")
    public ResponseResult<Instructor> findById(@PathVariable("id") int id) {
        return instructorService.readInstructorById(id);
    }

    /**
     * This endpoint calls a method of InstructorService to Update an Instructor with this id
     *
     * @param id
     * @return ResponseResult<Instructor>
     */
    @PutMapping("/{id}")
    public ResponseResult<Instructor> update(@PathVariable("id") int id, @RequestBody Instructor instructor) {
        return instructorService.updateInstructor(id, instructor);
    }

    /**
     * This endpoint call a method of InstructorService to Delete the Instructor with this id
     *
     * @param id
     * @return ResponseResult<Boolean>
     */
    @DeleteMapping("/{id}")
    public ResponseResult<Boolean> delete(@PathVariable("id") int id) {
        return instructorService.deleteInstructor(id);
    }

    @PutMapping("/assign/{instructorId}/{courseId}")
    public ResponseResult<Boolean> assignCourse(@PathVariable("instructorId") int instructorId, @PathVariable("courseId") int courseId ) throws Exception {
        return instructorService.assignCourse(instructorId,courseId);
    }

}
