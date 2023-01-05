package com.university.CourseManagement.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "Instructors")
public class Instructor {
    @Id
    private int id;
    private String name;


}
