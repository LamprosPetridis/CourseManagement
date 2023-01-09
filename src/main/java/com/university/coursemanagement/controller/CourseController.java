package com.university.coursemanagement.controller;

import com.university.coursemanagement.dto.ResponseResult;
import com.university.coursemanagement.model.Course;
import com.university.coursemanagement.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
     * @param courseId
     * @return ResponseResult<Course>
     */
    @GetMapping("/{courseId}")
    public ResponseResult<Course> findById(@PathVariable("courseId") Integer courseId) {
        return courseService.readCourse(courseId);
    }

    /**
     * This endpoint calls a method of CourseService to Get all Courses
     *
     * @return ResponseResult<Course>
     */
    @GetMapping("/")
    public ResponseResult<List<Course>> findAll() {
        return courseService.readCourses();
    }

    /**
     * This endpoint calls a method of CourseService to Update a Course with this id
     *
     * @param courseId
     * @return ResponseResult<Owner>
     */
    @PutMapping("/{courseId}")
    public ResponseResult<Course> update(@PathVariable("courseId") Integer courseId, @RequestBody Course course) {
        return courseService.updateCourse(courseId, course);
    }

    /**
     * This endpoint call a method of CourseService to Delete the Course with this id
     *
     * @param courseId
     * @return ResponseResult<Boolean>
     */
    @DeleteMapping("/{courseId}")
    public ResponseResult<Boolean> delete(@PathVariable("courseId") Integer courseId) {
        return courseService.deleteCourse(courseId);
    }

    /**
     * This endpoint call a method of CourseService to Delete all courses
     *
     * @return ResponseResult<Boolean>
     */
    @DeleteMapping("/")
    public ResponseResult<Boolean> delete() {
        return courseService.deleteCourses();
    }

    /**
     * This endpoint calls a method of CourseService to assign a person to a Course
     *
     * @param courseId, personId
     * @return ResponseResult<Boolean>
     * @throws Exception
     */
    @PutMapping("/{courseId}/{personId}")
    public ResponseResult<Boolean> assign(@PathVariable("courseId") Integer courseId, @PathVariable("personId") Integer personId) {
        return courseService.assignCourse(courseId,personId);
    }

    /**
     * This endpoint calls a method of CourseService to assign a person to multiple course
     *
     * @param personId, courseIds
     * @return ResponseResult<Boolean>
     * @throws Exception
     */
    @PutMapping("/assign/{personId}")
    public ResponseResult<Boolean> assignMultiple(@PathVariable("personId") Integer personId, @RequestBody List<Integer> courseIds) {
        return courseService.assignCourses(personId,courseIds);
    }

    /**
     * This endpoint calls a method of CourseService to assign a person to multiple course
     *
     * @param personId
     * @return ResponseResult<List<Course>>
     * @throws Exception
     */
    @GetMapping("/person/{personId}")
    public ResponseResult<List<Course>> viewCoursesByPerson(@PathVariable("personId") Integer personId) {
        return courseService.viewCoursesPerPerson(personId);
    }


}
