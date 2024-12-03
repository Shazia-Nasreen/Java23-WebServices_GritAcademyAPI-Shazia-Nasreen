package com.GritAcademyAPI.students;

import com.GritAcademyAPI.courses.CoursesDTO;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentsDTO {
    private Long id;
    private String fName;
    private String lName;
    private String town;
    List<CoursesDTO> courses;

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }


}

