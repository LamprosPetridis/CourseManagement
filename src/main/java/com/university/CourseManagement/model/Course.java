package com.university.CourseManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Courses")
public class Course {

    @Id
    private int id;

    private String name;

    @JsonIgnore
    @ManyToOne
    private Student student;

    @JsonIgnore
    @ManyToOne
    private Instructor instructor;

}
