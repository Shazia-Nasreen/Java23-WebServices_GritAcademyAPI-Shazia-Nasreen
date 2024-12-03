package com.GritAcademyAPI.students;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {

    List<Students> findByfName(String fName);

    List<Students> findBylName(String lName);

    List<Students> findByTown(String town);

}