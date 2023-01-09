package com.university.coursemanagement.repository;

import com.university.coursemanagement.model.Instructor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InstructorRepository extends CrudRepository<Instructor, Integer> {
}
