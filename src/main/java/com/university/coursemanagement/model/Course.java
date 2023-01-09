package com.university.coursemanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * This is the Course Model
 */
@Data
@Entity
@Table(name = "Courses")
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @SequenceGenerator(name = "course_gen", sequenceName = "course_gen", initialValue = 3000 )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_gen")
    @Column(name = "id")
    @NotNull
    private Integer courseId;
    @NotNull
    private String courseName;
    private String courseDescription;
    @NotNull
    private String yearTaught;
    @NotNull
    private String semester;
    @NotNull
    private String numberOfInstructors;
    @ManyToMany
    List<Instructor> instructors;
    @ManyToMany
    List<Student> students;
}
