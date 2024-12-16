package com.GritAcademyAPI.courses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Lombok annotation generates getters, setters, and other methods automatically
@NoArgsConstructor
@AllArgsConstructor
public class CoursesDTO {
    private Long id;
    private String name;
    private String description;
}
