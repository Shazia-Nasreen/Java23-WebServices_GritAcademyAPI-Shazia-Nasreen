package com.GritAcademyAPI.studentsCourses;

import com.GritAcademyAPI.courses.Courses;
import com.GritAcademyAPI.students.Students;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "students_courses")
@Table(name = "students_courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentsCourses {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "students_id",
            referencedColumnName = "id")
    private Students students;
    @ManyToOne
    @JoinColumn(name = "courses_id",
            referencedColumnName = "id")
    private Courses courses;

}
