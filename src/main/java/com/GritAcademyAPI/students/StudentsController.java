package com.GritAcademyAPI.students;

import com.GritAcademyAPI.courses.CoursesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value= "/api/students", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class StudentsController {
    @Autowired
    private final StudentsService studentsService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> getAllStudents() {
        List<StudentsDTO> students = studentsService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    @GetMapping(value = "/allAssociations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> getAllAssociationsBetweenStudentsAndCourses() {
        List<StudentsDTO> associations = studentsService.getAllAssociationsBetweenStudentsAndCourses();
        return new ResponseEntity<>(associations, HttpStatus.OK);
    }

    @GetMapping(value= "/searchCoursesByStudentId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CoursesDTO>> searchCoursesByStudentId(@RequestParam("id") Long id) {
        List<CoursesDTO> coursesDTO = studentsService.getCoursesForStudent(id);
        return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
    }
    @GetMapping(value= "/by-fname", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> getStudentsByFName(@RequestParam String fName) {
        List<StudentsDTO> students = studentsService.getStudentsByFName(fName);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping(value= "/by-lname", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> getStudentsByLName(@RequestParam String lName) {
        List<StudentsDTO> students = studentsService.getStudentsByLName(lName);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping(value= "/by-town", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> getStudentsByTown(@RequestParam String town) {
        List<StudentsDTO> students = studentsService.getStudentsByTown(town);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentsDTO> addStudent(@ModelAttribute StudentsDTO studentsDTO) {
        StudentsDTO addedStudent = studentsService.addStudent(studentsDTO);
        return new ResponseEntity<>(addedStudent, HttpStatus.CREATED);
    }

    @DeleteMapping(value= "/deleteStudentById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> deleteStudentById(@RequestParam("id") Long id) {
        studentsService.deleteStudentById(id);
        List<StudentsDTO> remainingStudents = studentsService.getAllStudents(); // Fetch all students after deletion
        return new ResponseEntity<>(remainingStudents, HttpStatus.OK);
    }


    @PostMapping(value= "/addStudentToCourse", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentsDTO> addStudentToCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        studentsService.addStudentToCourse(studentId, courseId);
        // Fetch updated student data including courses
        StudentsDTO updatedStudent = (StudentsDTO) studentsService.getStudentById(studentId);
        return new ResponseEntity<>(updatedStudent, HttpStatus.CREATED);
    }

    @DeleteMapping(value= "/removeStudentFromCourse", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentsDTO> removeStudentFromCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        studentsService.removeStudentFromCourse(studentId, courseId);
        // Fetch updated student data including courses
        StudentsDTO updatedStudent = (StudentsDTO) studentsService.getStudentById(studentId);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

}

