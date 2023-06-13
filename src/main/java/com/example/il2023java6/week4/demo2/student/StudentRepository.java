package com.example.il2023java6.week4.demo2.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
//    @Query("select s from Student s join s.")
//    List<Student> getStudentAndJunctionTable();
//
}
