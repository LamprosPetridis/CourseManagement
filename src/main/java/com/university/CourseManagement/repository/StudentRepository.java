package com.university.CourseManagement.repository;

import com.university.CourseManagement.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findStudentById(int id);

    Student findStudentByName(int name);

    boolean existsStudentById(int id);

    boolean existsStudentByName(String name);


}
