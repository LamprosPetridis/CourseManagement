package com.university.coursemanagement.repository;

import com.university.coursemanagement.model.Course;
import com.university.coursemanagement.model.Instructor;
import com.university.coursemanagement.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    Optional<List<Course>> findCoursesByInstructorsContaining(Instructor instructor);
    Optional<List<Course>> findCoursesByStudentsContaining(Student student);
}
