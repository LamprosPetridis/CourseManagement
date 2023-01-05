package com.university.CourseManagement.service;

import com.university.CourseManagement.dto.ResponseResult;
import com.university.CourseManagement.dto.ResponseStatus;
import com.university.CourseManagement.model.Course;
import com.university.CourseManagement.model.Instructor;
import com.university.CourseManagement.model.Student;
import com.university.CourseManagement.repository.CourseRepository;
import com.university.CourseManagement.repository.InstructorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class InstructorServiceImpl implements InstructorService{

    private InstructorRepository instructorRepository;
    private CourseRepository courseRepository;



    @Override
    public ResponseResult<Instructor> createInstructor(Instructor instructor){
        instructorRepository.save(instructor);
        return new ResponseResult(instructor, ResponseStatus.SUCCESS, "Instructor created successfully");
    }

    @Override
    public ResponseResult<Instructor> readInstructorById(int id) {
        Instructor instructor = instructorRepository.findInstructorById(id);
        if (instructor == null) {
            return new ResponseResult(null, ResponseStatus.INSTRUCTOR_NOT_FOUND, "Cannot find instructor with ID: " + id);
        } else {
            return new ResponseResult(instructor, ResponseStatus.SUCCESS, "Instructor found successfully");
        }
    }

    @Override
    public ResponseResult<Instructor> updateInstructor(int id, Instructor instructor) {
        Optional<Instructor> instructorDb = instructorRepository.findById(id);
        if (instructorDb.isPresent()) {
            instructorDb.get().setName(instructor.getName());
            instructorRepository.save(instructorDb.get());
            return new ResponseResult(instructorDb, ResponseStatus.SUCCESS, "Successfully updated instructor details.");
        } else {
            return new ResponseResult(null, ResponseStatus.INSTRUCTOR_NOT_UPDATED, "Update failed because instructor with ID: " + id + " doesn't exist");
        }
    }

    @Override
    public ResponseResult<Boolean> deleteInstructor(int id) {
        if (instructorRepository.findById(id) == null) {
            return new ResponseResult(false, ResponseStatus.INSTRUCTOR_NOT_DELETED, "Delete operation failed because instructor with ID: " + id + " doesn't exist");
        }

        instructorRepository.deleteById(id);
        return new ResponseResult(true, ResponseStatus.SUCCESS, "Instructor details deleted successfully");

    }

    @Override
    public ResponseResult<Boolean> assignCourse(int instructorId, int courseId) {
        Instructor instructor = instructorRepository.findInstructorById(instructorId);
        if (instructor == null) {
            return new ResponseResult(Boolean.FALSE, ResponseStatus.INSTRUCTOR_NOT_FOUND, "Cannot find instructor with ID: " + instructorId);
        } else {

            Optional<Course> courseDb = courseRepository.findById(courseId);
            if (courseDb.isPresent()) {
                List<Course> coursesAttended= instructor.getCourses();

                coursesAttended.add(courseDb.get());
                instructor.setCourses(coursesAttended);
                instructorRepository.save(instructor);
            }else{
                return new ResponseResult(Boolean.FALSE, ResponseStatus.COURSE_NOT_FOUND, "Cannot find course with ID: " + courseId);
            }

            return new ResponseResult(Boolean.TRUE, ResponseStatus.SUCCESS, "Successfully assigned course with ID " + courseId + " to instructor with ID"+ instructorId);

        }
    }
}
