package com.university.coursemanagement.service;

import com.university.coursemanagement.dto.ResponseResult;
import com.university.coursemanagement.dto.ResponseStatus;
import com.university.coursemanagement.model.Course;
import com.university.coursemanagement.model.Instructor;
import com.university.coursemanagement.model.Student;
import com.university.coursemanagement.repository.CourseRepository;
import com.university.coursemanagement.repository.InstructorRepository;
import com.university.coursemanagement.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class CourseServiceImpl implements CourseService{

    @Value("${maximum.courses.per.student}")
    private String maxNumberOfCourses;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseService courseService;



    @Override
    public ResponseResult<Course> createCourse(Course course){
        courseRepository.save(course);
        return new ResponseResult(course, ResponseStatus.SUCCESS, "Course created successfully");
    }

    @Override
    public ResponseResult<Course> readCourse(Integer courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (!course.isPresent()) {
            return new ResponseResult(null, ResponseStatus.COURSE_NOT_FOUND, "Cannot find course with ID: " + courseId);
        }
        return new ResponseResult(course.get(), ResponseStatus.SUCCESS, "Course found successfully");
    }

    @Override
    public ResponseResult<List<Course>> readCourses() {
        return new ResponseResult(courseRepository.findAll(), ResponseStatus.SUCCESS, "Returned list of all courses successfully");
    }

    @Override
    public ResponseResult<Course> updateCourse(Integer courseId, Course course) {
        Optional<Course> courseDb = courseRepository.findById(courseId);
        if (!courseDb.isPresent()) {
            return new ResponseResult(null, ResponseStatus.COURSE_NOT_UPDATED, "Update failed because course with ID: " + courseId + " doesn't exist");
        }
        courseDb.get().setCourseName(course.getCourseName());
        courseDb.get().setCourseDescription(course.getCourseDescription());
        courseDb.get().setYearTaught(course.getYearTaught());
        courseDb.get().setSemester(course.getSemester());
        courseDb.get().setNumberOfInstructors(course.getNumberOfInstructors());
        courseRepository.save(courseDb.get());
        return new ResponseResult(courseDb, ResponseStatus.SUCCESS, "Successfully updated course details.");
    }

    @Override
    public ResponseResult<Boolean> deleteCourse(Integer courseId) {
        if (!courseRepository.findById(courseId).isPresent()) {
            return new ResponseResult(false, ResponseStatus.COURSE_NOT_DELETED, "Delete operation failed because course with ID: " + courseId + " doesn't exist");
        }
        courseRepository.deleteById(courseId);
        return new ResponseResult(true, ResponseStatus.SUCCESS, "Course details deleted successfully");
    }

    @Override
    public ResponseResult<Boolean> deleteCourses() {
        courseRepository.deleteAll();
        return new ResponseResult(true, ResponseStatus.SUCCESS, "All courses entries deleted successfully");
    }

    @Override
    public ResponseResult<Boolean> assignCourse(Integer courseId, Integer personId) {
        Optional<Course> courseDb = courseRepository.findById(courseId);
        if (!courseDb.isPresent()) {
            return new ResponseResult(false, ResponseStatus.COURSE_NOT_ASSIGNED, "Course assignment failed because course with ID: " + courseId + " doesn't exist");
        }
        List<Course> courseList = courseService.viewCoursesPerPerson(personId).getData();
        if (personId.toString().charAt(0) == '1') {
            Optional<Instructor> instructor = instructorRepository.findById(personId);
            if (!instructor.isPresent()) {
                return new ResponseResult(Boolean.FALSE, ResponseStatus.INSTRUCTOR_NOT_FOUND, "Cannot find instructor with ID: " + personId);
            }
            List<Instructor> instructorList = courseDb.get().getInstructors();
            if(instructorList.contains(instructor)){
                return new ResponseResult(Boolean.FALSE, ResponseStatus.COURSE_ALREADY_INSTRUCTOR, "The instructor with instructor ID : "+ personId + " already attends course with course ID : "+ courseId);
            }
            if (instructorList.size() >= Integer.parseInt(courseDb.get().getNumberOfInstructors())) {
                return new ResponseResult(Boolean.FALSE, ResponseStatus.COURSE_MAX_INSTRUCTORS, "Maximum number of instructors for course Id : " + courseId + " is " + courseDb.get().getNumberOfInstructors());
            }

            instructorList.add(instructor.get());
            courseDb.get().setInstructors(instructorList);
            courseRepository.save(courseDb.get());
        } else if (personId.toString().charAt(0) == '2') {
            Optional<Student> student = studentRepository.findById(personId);
            if (!student.isPresent()) {
                return new ResponseResult(Boolean.FALSE, ResponseStatus.STUDENT_NOT_FOUND, "Cannot find student with ID: " + personId);
            }
            if(courseList.contains(courseDb.get())){
                return new ResponseResult(Boolean.FALSE, ResponseStatus.COURSE_ALREADY_STUDENT, "The student with student ID : "+ personId + " already attends course with course ID : "+ courseId);
            }
            if (courseList.size() >= Integer.parseInt(maxNumberOfCourses)) {
                return new ResponseResult(Boolean.FALSE, ResponseStatus.COURSE_MAX_STUDENTS, "Maximum number of courses a student can attend is " + maxNumberOfCourses);
            }
            List<Student> studentList = courseDb.get().getStudents();
            studentList.add(student.get());
            courseDb.get().setStudents(studentList);
            courseRepository.save(courseDb.get());
        }
        return new ResponseResult(true, ResponseStatus.SUCCESS, "Course assigned successfully");
    }

    @Override
    public ResponseResult<Boolean> assignCourses(Integer personId, List<Integer> courseIds) {
        if(courseIds.isEmpty()){
            return new ResponseResult(false, ResponseStatus.FAILURE, "No courses were selected to be assigned");
        }
        for(int i=0;i<=courseIds.size()-1;i++){
            courseService.assignCourse(courseIds.get(i),personId);
        }
        return new ResponseResult(true, ResponseStatus.SUCCESS, "All courses assigned successfully");
    }

    @Override
    public ResponseResult<List<Course>> viewCoursesPerPerson(Integer personId) {
        Optional<List<Course>> courseList = null;
        if (personId.toString().charAt(0) == '1') {
            Optional<Instructor> instructor = instructorRepository.findById(personId);
            if (!instructor.isPresent()) {
                return new ResponseResult(Boolean.FALSE, ResponseStatus.INSTRUCTOR_NOT_FOUND, "Cannot find instructor with ID: " + personId);
            }
            courseList = courseRepository.findCoursesByInstructorsContaining(instructor.get());
        }else if (personId.toString().charAt(0) == '2') {
            Optional<Student> student = studentRepository.findById(personId);
            if (!student.isPresent()) {
                return new ResponseResult(Boolean.FALSE, ResponseStatus.STUDENT_NOT_FOUND, "Cannot find student with ID: " + personId);
            }
            courseList = courseRepository.findCoursesByStudentsContaining(student.get());
        }
        return new ResponseResult(courseList.get(), ResponseStatus.SUCCESS, "Successfully returned list of all courses attended");
    }
}
