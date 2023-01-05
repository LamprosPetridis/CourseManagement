package com.university.CourseManagement.service;

import com.university.CourseManagement.dto.ResponseResult;
import com.university.CourseManagement.dto.ResponseStatus;
import com.university.CourseManagement.model.Instructor;
import com.university.CourseManagement.repository.InstructorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class InstructorServiceImpl implements InstructorService{

    private InstructorRepository instructorRepository;



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
}
