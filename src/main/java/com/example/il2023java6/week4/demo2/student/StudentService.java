package com.example.il2023java6.week4.demo2.student;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<Student> getAllStudents();
}
