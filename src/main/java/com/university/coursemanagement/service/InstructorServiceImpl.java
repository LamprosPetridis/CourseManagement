package com.university.coursemanagement.service;

import com.university.coursemanagement.dto.ResponseResult;
import com.university.coursemanagement.dto.ResponseStatus;
import com.university.coursemanagement.model.Course;
import com.university.coursemanagement.model.Instructor;
import com.university.coursemanagement.repository.CourseRepository;
import com.university.coursemanagement.repository.InstructorRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class InstructorServiceImpl implements InstructorService{

    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    public ResponseResult<Instructor> createInstructor(Instructor instructor){
        instructorRepository.save(instructor);
        return new ResponseResult(instructor, ResponseStatus.SUCCESS, "Instructor created successfully");
    }

    @Override
    public ResponseResult<Instructor> readInstructorById(Integer instructorId) {
        Optional<Instructor> instructor = instructorRepository.findById(instructorId);
        if (!instructor.isPresent()) {
            return new ResponseResult(null, ResponseStatus.INSTRUCTOR_NOT_FOUND, "Cannot find instructor with ID: " + instructorId);
        }
        return new ResponseResult(instructor, ResponseStatus.SUCCESS, "Instructor found successfully");
    }

    @Override
    public ResponseResult<List<Instructor>> readInstructors() {
        return new ResponseResult(instructorRepository.findAll(), ResponseStatus.SUCCESS, "Returned list of all instructors successfully");
    }

    @Override
    public ResponseResult<Instructor> updateInstructor(Integer instructorId, Instructor instructor) {
        Optional<Instructor> instructorDb = instructorRepository.findById(instructorId);
        if (!instructorDb.isPresent()) {
            return new ResponseResult(null, ResponseStatus.INSTRUCTOR_NOT_UPDATED, "Update failed because instructor with ID: " + instructorId + " doesn't exist");
        }
        instructorDb.get().setFirstName(instructor.getFirstName());
        instructorDb.get().setLastName(instructor.getLastName());
        instructorDb.get().setAddress(instructor.getAddress());
        instructorDb.get().setPhoneNumber(instructor.getPhoneNumber());
        instructorDb.get().setEmail(instructor.getEmail());
        instructorDb.get().setVatNumber(instructor.getEmail());
        instructorRepository.save(instructorDb.get());
        return new ResponseResult(instructorDb, ResponseStatus.SUCCESS, "Successfully updated instructor details.");

    }

    @Override
    public ResponseResult<Boolean> deleteInstructor(Integer instructorId) {
        Optional<Instructor> instructorDb = instructorRepository.findById(instructorId);
        if (!instructorDb.isPresent()) {
            return new ResponseResult(false, ResponseStatus.INSTRUCTOR_NOT_DELETED, "Delete operation failed because instructor with ID: " + instructorId + " doesn't exist");
        }
        instructorRepository.deleteById(instructorId);
        return new ResponseResult(true, ResponseStatus.SUCCESS, "Instructor details deleted successfully");
    }

    @Override
    public ResponseResult<Boolean> deleteAllInstructors() {
        instructorRepository.deleteAll();
        return new ResponseResult(true, ResponseStatus.SUCCESS, "All instructor entries deleted successfully");
    }
}
