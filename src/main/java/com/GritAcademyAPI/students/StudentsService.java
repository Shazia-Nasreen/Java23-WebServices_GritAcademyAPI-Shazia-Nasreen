package com.GritAcademyAPI.students;

import com.GritAcademyAPI.courses.Courses;
import com.GritAcademyAPI.courses.CoursesDTO;
import com.GritAcademyAPI.courses.CoursesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentsService {
    @Autowired
    private final StudentsRepository studentsRepository;
    @Autowired
    private final CoursesRepository coursesRepository;
    @Transactional(readOnly = true)
    //All students
    public List<StudentsDTO> getAllStudents() {
        List<StudentsDTO> studentsDTO = new ArrayList<>();
        studentsRepository.findAll().forEach(student -> studentsDTO.add(this.stuMapToDTO(student)));
        return studentsDTO;
    }
    //All Associations between Students and Courses
    @Transactional(readOnly = true)
    public List<StudentsDTO> getAllAssociationsBetweenStudentsAndCourses() {
        List<StudentsDTO> studentsDTO = new ArrayList<>();
        studentsRepository.findAll().forEach(student -> studentsDTO.add(mapToDTO(student)));
        return studentsDTO;
    }
    //A student with a specific ID and its courses
    @Transactional(readOnly = true)
    public List<CoursesDTO> getCoursesForStudent(Long id) {
        Optional<Students> studentOptional = studentsRepository.findById(id);
        if (studentOptional.isPresent()) {
            Students student = studentOptional.get();
            // Eagerly fetch courses to avoid lazy initialization exception
            student.getCourses().size();
            return student.getCourses().stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
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
        dto.setCourses(student.getCourses().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList()));

        return dto;
    }
    private StudentsDTO stuMapToDTO(Students student) {
        StudentsDTO dto = new StudentsDTO();
        dto.setId(student.getId());
        dto.setfName(student.getfName());
        dto.setlName(student.getlName());
        dto.setTown(student.getTown());
        return dto;
    }
    @Transactional(readOnly = true)
    //All students with a specific fname and their courses
    public List<StudentsDTO> getStudentsByFName(String fName) {
        List<Students> students = studentsRepository.findByfName(fName);
        return students.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    //All students with a specific lname and their courses
    public List<StudentsDTO> getStudentsByLName(String lName) {
        List<Students> students = studentsRepository.findBylName(lName);
        return students.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    //All Students from a Specific City and their Courses
    public List<StudentsDTO> getStudentsByTown(String town) {
        List<Students> students = studentsRepository.findByTown(town);
        return students.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    @Transactional
    //Add a Student
    public StudentsDTO addStudent(StudentsDTO studentsDTO) {
        Students students = new Students();
        // Set student details from DTO
        students.setfName(studentsDTO.getfName());
        students.setlName(studentsDTO.getlName());
        students.setTown(studentsDTO.getTown());
        // Save the student to the database
        Students savedStudent = studentsRepository.save(students);
        // Map the saved student to DTO
        return stuMapToDTO(savedStudent);
    }
    @Transactional
    //Remove a Student via ID
    public void deleteStudentById(Long id) {
        studentsRepository.deleteById(id);
    }
    @Transactional
    public void addStudentToCourse(Long studentId, Long courseId) {
        Optional<Students> studentOptional = studentsRepository.findById(studentId);
        Optional<Courses> courseOptional = coursesRepository.findById(courseId);

        if (studentOptional.isPresent() && courseOptional.isPresent()) {
            Students student = studentOptional.get();
            Courses course = courseOptional.get();

            student.getCourses().add(course);
            course.getStudents().add(student);


        } else {
            throw new ResourceNotFoundException("Student or Course not found");
        }
    }

    @Transactional
    public void removeStudentFromCourse(Long studentId, Long courseId) {
        Optional<Students> studentOptional = studentsRepository.findById(studentId);
        Optional<Courses> courseOptional = coursesRepository.findById(courseId);

        if (studentOptional.isPresent() && courseOptional.isPresent()) {
            Students student = studentOptional.get();
            Courses course = courseOptional.get();

            student.getCourses().remove(course);
            //studentsRepository.save(student);
        } else {
            throw new ResourceNotFoundException("Student or Course not found");
        }
    }
    @Transactional(readOnly = true)
    public StudentsDTO getStudentById(Long id) {
        Optional<Students> studentOptional = studentsRepository.findById(id);
        if (studentOptional.isPresent()) {
            return mapToDTO(studentOptional.get());
        } else {
            throw new ResourceNotFoundException("Student not found with ID: " + id);
        }
    }
}

