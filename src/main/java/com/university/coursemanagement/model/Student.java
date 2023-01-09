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
 * This is the Student Model
 */
@Data
@Entity
@Table(name = "Students")
@AllArgsConstructor
@NoArgsConstructor
public class Student {


    @Id
    @SequenceGenerator(name = "student_gen", sequenceName = "student_gen", initialValue = 2000 )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_gen")
   //@Column(name = "id")
    @NotNull
    private Integer studentId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String address;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String email;
    @NotNull
    private String vatNumber;
}
