package com.university.CourseManagement.service;

import com.university.CourseManagement.dto.ResponseResult;
import com.university.CourseManagement.dto.ResponseStatus;
import com.university.CourseManagement.model.Course;
import com.university.CourseManagement.model.Instructor;
import com.university.CourseManagement.model.Student;
import com.university.CourseManagement.repository.CourseRepository;
import com.university.CourseManagement.repository.InstructorRepository;
import com.university.CourseManagement.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;
    private InstructorRepository instructorRepository;
    private StudentRepository studentRepository;



    @Override
    public ResponseResult<Course> createCourse(Course course){
        courseRepository.save(course);
        return new ResponseResult(course, ResponseStatus.SUCCESS, "Course created successfully");
    }

    @Override
    public ResponseResult<Course> readCourseById(int id) {
        Course course = courseRepository.findCourseById(id);
        if (course == null) {
            return new ResponseResult(null, ResponseStatus.COURSE_NOT_FOUND, "Cannot find course with ID: " + id);
        } else {
            return new ResponseResult(course, ResponseStatus.SUCCESS, "Course found successfully");
        }
    }

    @Override
    public ResponseResult<Course> updateCourse(int id, Course course) {
        Optional<Course> courseDb = courseRepository.findById(id);
        if (courseDb.isPresent()) {
            courseDb.get().setName(course.getName());
            courseRepository.save(courseDb.get());
            return new ResponseResult(courseDb, ResponseStatus.SUCCESS, "Successfully updated course details.");
        } else {
            return new ResponseResult(null, ResponseStatus.COURSE_NOT_UPDATED, "Update failed because course with ID: " + id + " doesn't exist");
        }
    }

    @Override
    public ResponseResult<Boolean> deleteCourse(int id) {
        if (courseRepository.findById(id) == null) {
            return new ResponseResult(false, ResponseStatus.COURSE_NOT_DELETED, "Delete operation failed because course with ID: " + id + " doesn't exist");
        }

        courseRepository.deleteById(id);
        return new ResponseResult(true, ResponseStatus.SUCCESS, "Course details deleted successfully");

    }

//    @Override
//    public ResponseResult<Boolean> assignCourse(int courseId, int id) {
//        Optional<Student> studentDb = studentRepository.findById(id);
//        Optional<Instructor> instructorDb = instructorRepository.findById(id);
//        Optional<Course> courseDb = courseRepository.findById(id);
//
//
//        if (courseDb.isPresent()) {
//
//        } else {
//            return new ResponseResult(null, ResponseStatus.COURSE_NOT_FOUND, "Cannot find course with ID: " + id);
//        }
//
//      return null;
//    }
}
