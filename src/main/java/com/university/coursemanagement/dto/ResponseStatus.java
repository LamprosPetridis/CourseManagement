package com.university.coursemanagement.dto;

public enum ResponseStatus {
    SUCCESS,

    FAILURE,
    STUDENT_NOT_CREATED,
    STUDENT_NOT_FOUND,
    STUDENT_NOT_UPDATED,
    STUDENT_NOT_DELETED,
    INSTRUCTOR_NOT_CREATED,
    INSTRUCTOR_NOT_FOUND,
    INSTRUCTOR_NOT_UPDATED,
    INSTRUCTOR_NOT_DELETED,
    COURSE_NOT_CREATED,
    COURSE_NOT_FOUND,
    COURSE_NOT_UPDATED,
    COURSE_NOT_DELETED,
    COURSE_NOT_ASSIGNED,
    COURSE_MAX_INSTRUCTORS,
    COURSE_MAX_STUDENTS,

    COURSE_ALREADY_STUDENT,

    COURSE_ALREADY_INSTRUCTOR
}