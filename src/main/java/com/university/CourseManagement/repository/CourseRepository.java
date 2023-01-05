package com.university.CourseManagement.repository;

import com.university.CourseManagement.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    Course findCourseById(int id);

    Course findCourseByName(int name);

    boolean existsCourseById(int id);

    boolean existsCourseByName(String name);

}
