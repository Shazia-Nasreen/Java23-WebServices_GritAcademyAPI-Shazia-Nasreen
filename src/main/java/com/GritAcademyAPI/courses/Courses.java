package com.GritAcademyAPI.courses;

import com.GritAcademyAPI.students.Students;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.*;



import java.util.HashSet;
import java.util.Set;
@Entity(name = "courses")
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "courses",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Students> students = new HashSet<>();
}
