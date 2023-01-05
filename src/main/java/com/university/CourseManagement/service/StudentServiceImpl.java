package com.university.CourseManagement.service;

import com.university.CourseManagement.dto.ResponseResult;
import com.university.CourseManagement.dto.ResponseStatus;
import com.university.CourseManagement.model.Course;
import com.university.CourseManagement.model.Student;
import com.university.CourseManagement.repository.CourseRepository;
import com.university.CourseManagement.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;



    @Override
    public ResponseResult<Student> createStudent(Student student){
        studentRepository.save(student);
        return new ResponseResult(student, ResponseStatus.SUCCESS, "Student created successfully");
    }

    @Override
    public ResponseResult<Student> readStudentById(int id) {
        Student student = studentRepository.findStudentById(id);
        if (student == null) {
            return new ResponseResult(null, ResponseStatus.STUDENT_NOT_FOUND, "Cannot find student with ID: " + id);
        } else {
            return new ResponseResult(student, ResponseStatus.SUCCESS, "Student found successfully");
        }
    }

    @Override
    public ResponseResult<Student> updateStudent(int id, Student student) {
        Optional<Student> studentDb = studentRepository.findById(id);
        if (studentDb.isPresent()) {
            studentDb.get().setName(student.getName());
            studentRepository.save(studentDb.get());
            return new ResponseResult(studentDb, ResponseStatus.SUCCESS, "Successfully updated student details.");
        } else {
            return new ResponseResult(null, ResponseStatus.STUDENT_NOT_UPDATED, "Update failed because student with ID: " + id + " doesn't exist");
        }
    }

    @Override
    public ResponseResult<Boolean> deleteStudent(int id) {
        if (studentRepository.findById(id) == null) {
            return new ResponseResult(false, ResponseStatus.STUDENT_NOT_DELETED, "Delete operation failed because student with ID: " + id + " doesn't exist");
        }

        studentRepository.deleteById(id);
        return new ResponseResult(true, ResponseStatus.SUCCESS, "Student details deleted successfully");

    }

    @Override
    public ResponseResult<Boolean> assignCourse(int studentId, int courseId) {
        Student student = studentRepository.findStudentById(studentId);
        if (student == null) {
            return new ResponseResult(Boolean.FALSE, ResponseStatus.STUDENT_NOT_FOUND, "Cannot find student with ID: " + studentId);
        } else {

            Optional<Course> courseDb = courseRepository.findById(courseId);
            if (courseDb.isPresent()) {
                List<Course> coursesAttended= student.getCourses();

                if(coursesAttended.size() >= 5){
                    return new ResponseResult(Boolean.FALSE, ResponseStatus.STUDENT_MAX_COURSES, "Student with ID:  " + studentId + " already attends maximum number of Courses (5) ");
                }
                coursesAttended.add(courseDb.get());
                student.setCourses(coursesAttended);
                studentRepository.save(student);
            }else{
                return new ResponseResult(Boolean.FALSE, ResponseStatus.COURSE_NOT_FOUND, "Cannot find course with ID: " + courseId);
            }

            return new ResponseResult(Boolean.TRUE, ResponseStatus.SUCCESS, "Successfully assigned course with ID " + courseId + " to student with ID"+ studentId);

        }
    }
}
