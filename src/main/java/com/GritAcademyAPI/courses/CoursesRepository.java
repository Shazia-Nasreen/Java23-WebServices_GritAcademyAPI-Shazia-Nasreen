package com.GritAcademyAPI.courses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {
    List<Courses> findByNameContaining(String name);
    List<Courses> findByDescriptionContaining(String description);
}
