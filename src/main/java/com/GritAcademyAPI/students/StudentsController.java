package com.GritAcademyAPI.students;

import com.GritAcademyAPI.courses.CoursesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/students", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(value = "/searchCoursesByStudentId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CoursesDTO>> searchCoursesByStudentId(@RequestParam("id") Long id) {
        List<CoursesDTO> coursesDTO = studentsService.getCoursesForStudent(id);
        return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/by-fname", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> getStudentsByFName(@RequestParam String fName) {
        List<StudentsDTO> students = studentsService.getStudentsByFName(fName);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping(value = "/by-lname", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> getStudentsByLName(@RequestParam String lName) {
        List<StudentsDTO> students = studentsService.getStudentsByLName(lName);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping(value = "/by-town", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> getStudentsByTown(@RequestParam String town) {
        List<StudentsDTO> students = studentsService.getStudentsByTown(town);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentsDTO> addStudent(@ModelAttribute StudentsDTO studentsDTO) {
        StudentsDTO addedStudent = studentsService.addStudent(studentsDTO);
        return new ResponseEntity<>(addedStudent, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/deleteStudentById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> deleteStudentById(@RequestParam("id") Long id) {
        studentsService.deleteStudentById(id);
        List<StudentsDTO> remainingStudents = studentsService.getAllStudents(); // Fetch all students after deletion
        return new ResponseEntity<>(remainingStudents, HttpStatus.OK);
    }

    @PostMapping(value = "/addStudentToCourse", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentsDTO> addStudentToCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        studentsService.addStudentToCourse(studentId, courseId);
        // Fetch updated student data including courses
        StudentsDTO updatedStudent = studentsService.getStudentById(studentId);
        return new ResponseEntity<>(updatedStudent, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/removeStudentFromCourse", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentsDTO> removeStudentFromCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        studentsService.removeStudentFromCourse(studentId, courseId);
        // Fetch updated student data including courses
        StudentsDTO updatedStudent = studentsService.getStudentById(studentId);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @GetMapping("/table")
    public Map<String, Object> getStudentsAsTable() {
        List<StudentsDTO> students = studentsService.getAllStudents();

        // Create the table structure
        List<Map<String, Object>> rows = students.stream().map(student -> {
            // Create a map for the row data
            Map<String, Object> row = new HashMap<>();

            // Add student data to the row map
            row.put("ID", student.getId());
            row.put("First Name", student.getfName());
            row.put("Last Name", student.getlName());
            row.put("Town", student.getTown());

            // Handle the courses: if courses are not null, join them as a string
            String courses = student.getCourses() != null && !student.getCourses().isEmpty()
                    ? student.getCourses().stream()
                    .map(CoursesDTO::getName)  // Get the course name
                    .collect(Collectors.joining(", "))  // Join with comma
                    : "No courses";  // If no courses, return this

            row.put("Courses", courses);  // Add the courses data to the row

            return row;  // Return the row map
        }).collect(Collectors.toList());  // Collect all rows into a list

        // Return the headers and rows as a map
        Map<String, Object> response = new HashMap<>();
        response.put("headers", List.of("ID", "First Name", "Last Name", "Town", "Courses"));
        response.put("rows", rows);

        return response;
    }
}
