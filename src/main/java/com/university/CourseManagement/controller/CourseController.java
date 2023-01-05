package com.university.CourseManagement.controller;

import com.university.CourseManagement.dto.ResponseResult;
import com.university.CourseManagement.model.Course;
import com.university.CourseManagement.model.Course;
import com.university.CourseManagement.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * This is a Rest Controller which calls The Course Service (Dependency Injection)
 */

@RestController
@AllArgsConstructor
@RequestMapping("/course")
public class CourseController {


    @Autowired
    private CourseService courseService;

    /**
     * This endpoint calls a method of CourseService to Create a Course
     *
     * @param course
     * @return ResponseResult<Course>
     * @throws Exception
     */
    @PostMapping("/create")
    public ResponseResult<Course> create(@RequestBody Course course) throws Exception {
        return courseService.createCourse(course);
    }

    /**
     * This endpoint calls a method of CourseService to Get a Course
     *
     * @param id
     * @return ResponseResult<Course>
     */
    @GetMapping("/{id}")
    public ResponseResult<Course> findByVat(@PathVariable("id") int id) {
        return courseService.readCourseById(id);
    }

    /**
     * This endpoint calls a method of CourseService to Update a Course with this id
     *
     * @param id
     * @return ResponseResult<Owner>
     */
    @PutMapping("/{id}")
    public ResponseResult<Course> update(@PathVariable("id") int id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    /**
     * This endpoint call a method of CourseService to Delete the Course with this id
     *
     * @param id
     * @return ResponseResult<Boolean>
     */
    @DeleteMapping("/{id}")
    public ResponseResult<Boolean> delete(@PathVariable("id") int id) {
        return courseService.deleteCourse(id);
    }




}
