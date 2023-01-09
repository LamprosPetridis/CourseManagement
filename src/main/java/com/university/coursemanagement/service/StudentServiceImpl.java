package com.university.coursemanagement.service;

import com.university.coursemanagement.dto.ResponseResult;
import com.university.coursemanagement.dto.ResponseStatus;
import com.university.coursemanagement.model.Course;
import com.university.coursemanagement.model.Student;
import com.university.coursemanagement.repository.CourseRepository;
import com.university.coursemanagement.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public ResponseResult<Student> createStudent(Student student){
        studentRepository.save(student);
        return new ResponseResult(student, ResponseStatus.SUCCESS, "Student created successfully");
    }

    @Override
    public ResponseResult<Student> readStudent(Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (!student.isPresent()) {
            return new ResponseResult(null, ResponseStatus.STUDENT_NOT_FOUND, "Cannot find student with ID: " + studentId);
        }
        return new ResponseResult(student, ResponseStatus.SUCCESS, "Student found successfully");
    }

    @Override
    public ResponseResult<List<Student>> readStudents() {
        return new ResponseResult(studentRepository.findAll(), ResponseStatus.SUCCESS, "Returned list of all students successfully");
    }


    @Override
    public ResponseResult<Student> updateStudent(Integer studentId, Student student) {
        Optional<Student> studentDb = studentRepository.findById(studentId);
        if (!studentDb.isPresent()) {
            return new ResponseResult(null, ResponseStatus.STUDENT_NOT_UPDATED, "Update failed because student with ID: " + studentId + " doesn't exist");
        }
        studentDb.get().setFirstName(student.getFirstName());
        studentDb.get().setLastName(student.getLastName());
        studentDb.get().setAddress(student.getAddress());
        studentDb.get().setPhoneNumber(student.getPhoneNumber());
        studentDb.get().setEmail(student.getEmail());
        studentDb.get().setVatNumber(student.getVatNumber());
        studentRepository.save(studentDb.get());
        return new ResponseResult(student, ResponseStatus.SUCCESS, "Student details updated successfully");
    }

    @Override
    public ResponseResult<Boolean> deleteStudent(Integer studentId) {
        Optional<Student> studentDb = studentRepository.findById(studentId);
        if (!studentDb.isPresent()) {
            return new ResponseResult(false, ResponseStatus.STUDENT_NOT_DELETED, "Delete operation failed because student with ID: " + studentId + " doesn't exist");
        }
        studentRepository.deleteById(studentId);
        return new ResponseResult(true, ResponseStatus.SUCCESS, "Student details deleted successfully");
    }

    @Override
    public ResponseResult<Boolean> deleteAllStudents() {
        studentRepository.deleteAll();
        return new ResponseResult(true, ResponseStatus.SUCCESS, "All student entries deleted successfully");
    }
}
