package com.schoolofliberation.academic.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete( sql = "UPDATE students SET students.delete_student = CURRENT_DATE WHERE ID = ?")
@Where(clause = "delete_student is null")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "dni")
    private Long dni;

    @ManyToOne
    @JoinColumn(name = "student_status")
    private Type studentStatus;
    
    @Column(name = "student_career")
    private Short studentCareer;

    @Column(name = "delete_student")
    private LocalDate deleteStudent;
}
