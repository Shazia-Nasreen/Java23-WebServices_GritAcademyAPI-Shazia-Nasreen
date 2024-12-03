package com.GritAcademyAPI.courses;

import com.GritAcademyAPI.students.StudentsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value= "/api/courses", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CoursesController {
    @Autowired
    private final CoursesService coursesService;

    @GetMapping(value= "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CoursesDTO>> getAllCourses() {
        List<CoursesDTO> courses = coursesService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
    @GetMapping(value= "/searchStudentsByCourseId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> getStudentsByCourseId(@RequestParam("id") Long id) {
        List<StudentsDTO> students = coursesService.getStudentsByCourseId(id);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    @GetMapping(value= "/by-name-keyword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CoursesDTO>> getCoursesByKeywordInName(@RequestParam String keyword) {
        List<CoursesDTO> courses = coursesService.getCoursesByKeywordInName(keyword);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
    @GetMapping(value= "/by-description-keyword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CoursesDTO>> getCoursesByKeywordInDescription(@RequestParam String keyword) {
        List<CoursesDTO> courses = coursesService.getCoursesByKeywordInDescription(keyword);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
    // Add a Course
    @PostMapping
    public ResponseEntity<CoursesDTO> addCourse(@ModelAttribute CoursesDTO courseDTO) {
        CoursesDTO addedCourse = coursesService.addCourse(courseDTO);
        return new ResponseEntity<>(addedCourse, HttpStatus.CREATED);
    }

    @DeleteMapping(value= "/deleteCourseById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CoursesDTO>> deleteCourseById(@RequestParam("id") Long id) {
        coursesService.deleteCourseById(id);
        List<CoursesDTO> remainingCourses = coursesService.getAllCourses(); // Fetch all students after deletion
        return new ResponseEntity<>(remainingCourses, HttpStatus.OK);
    }

}

