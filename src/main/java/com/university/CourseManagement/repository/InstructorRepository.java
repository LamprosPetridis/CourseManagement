package com.university.CourseManagement.repository;

import com.university.CourseManagement.model.Instructor;
import org.springframework.data.repository.CrudRepository;

public interface InstructorRepository extends CrudRepository<Instructor, Integer> {

    Instructor findInstructorById(int id);

    Instructor findInstructorByName(int name);

    boolean existsInstructorById(int id);

    boolean existsInstructorByName(String name);

}
