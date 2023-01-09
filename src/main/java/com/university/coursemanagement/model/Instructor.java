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
 * This is the Instructor Model
 */
@Data
@Entity
@Table(name = "Instructors")
@AllArgsConstructor
@NoArgsConstructor
public class Instructor {
    @Id
    @SequenceGenerator(name = "instructor_gen", sequenceName = "instructor_gen", initialValue = 1000 )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_gen")
    //@Column(name = "id")
    @NotNull
    private Integer instructorId;
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
