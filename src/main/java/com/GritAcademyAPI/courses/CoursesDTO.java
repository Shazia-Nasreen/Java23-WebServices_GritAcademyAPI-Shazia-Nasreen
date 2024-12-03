package com.GritAcademyAPI.courses;

import com.GritAcademyAPI.students.StudentsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursesDTO {
    private Long id;
    private String name;
    private String description;
    //List<StudentsDTO> students;
}

