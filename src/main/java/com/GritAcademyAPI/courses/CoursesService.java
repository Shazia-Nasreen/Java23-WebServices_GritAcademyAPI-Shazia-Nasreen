package com.GritAcademyAPI.courses;

import com.GritAcademyAPI.students.Students;
import com.GritAcademyAPI.students.StudentsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class CoursesService {
    @Autowired
    private final CoursesRepository coursesRepository;
    //All Courses
    public List<CoursesDTO> getAllCourses() {
        List<CoursesDTO> coursesDTO = new ArrayList<>();
        coursesRepository.findAll().forEach(course -> coursesDTO.add(this.mapToDTO(course)));
        return coursesDTO;
    }
    private CoursesDTO mapToDTO (Courses course){
        CoursesDTO dto= new CoursesDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());

        return dto;
    }
    private StudentsDTO mapToDTO(Students student) {
        StudentsDTO dto = new StudentsDTO();
        dto.setId(student.getId());
        dto.setfName(student.getfName());
        dto.setlName(student.getlName());
        dto.setTown(student.getTown());
        // Mapping courses associated with the student
        /*dto.setCourses(student.getCourses().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList()));*/

        return dto;
    }
    //A Course with a Specific ID and its Students
    public List<StudentsDTO> getStudentsByCourseId(Long id) {
        // Find the course by its ID
        Optional<Courses> course = coursesRepository.findById(id);
        if (course.isPresent()) {
            Courses courseEntity = course.get();
            // Eagerly fetch students to avoid lazy initialization exception
            courseEntity.getStudents().size();
            // Map each student to DTO and collect them into a list
            return courseEntity.getStudents().stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    //A Course with a Specific Word in the Name
    public List<CoursesDTO> getCoursesByKeywordInName(String keyword) {
        List<Courses> courses = coursesRepository.findByNameContaining(keyword);
        return courses.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    //A Course with a Specific Word in the Description
    public List<CoursesDTO> getCoursesByKeywordInDescription(String keyword) {
        List<Courses> courses = coursesRepository.findByDescriptionContaining(keyword);
        return courses.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    public CoursesDTO addCourse(CoursesDTO courseDTO) {
        Courses course = new Courses();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        Courses savedCourse = coursesRepository.save(course);
        return mapToDTO(savedCourse);
    }
    //Delete a Course via ID
    public void deleteCourseById(Long id) {
        coursesRepository.deleteById(id);
    }


}

