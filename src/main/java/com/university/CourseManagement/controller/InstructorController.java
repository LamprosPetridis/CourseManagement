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
     * This endpoint calls a method of InstructorService to Create a Instructor
     *
     * @param Instructor
     * @return ResponseResult<Instructor>
     * @throws Exception
     */
    @PostMapping("/create")
    public ResponseResult<Instructor> create(@RequestBody Instructor instructor) throws Exception {
        return instructorService.createInstructor(instructor);
    }

    /**
     * This endpoint calls a method of InstructorService to Get a Instructor
     *
     * @param id
     * @return ResponseResult<Instructor>
     */
    @GetMapping("/{id}")
    public ResponseResult<Instructor> findByVat(@PathVariable("id") int id) {
        return instructorService.readInstructorById(id);
    }

    /**
     * This endpoint calls a method of InstructorService to Update a Instructor with this id
     *
     * @param id
     * @return ResponseResult<Owner>
     */
    @PutMapping("/{id}")
    public ResponseResult<Instructor> update(@PathVariable("id") int id, @RequestBody Instructor instructor) {
        return instructorService.updateInstructor(id, instructor);
    }

    /**
     * This endpoint call a method of InstructorService to Delete the Instructor with this id
     *
     * @param id
     * @return ResponseResult<Owner>
     */
    @DeleteMapping("/{id}")
    public ResponseResult<Boolean> delete(@PathVariable("id") int id) {
        return instructorService.deleteInstructor(id);
    }


}
