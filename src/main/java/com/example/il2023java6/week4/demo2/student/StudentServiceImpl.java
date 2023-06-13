package com.example.il2023java6.week4.demo2.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }
}
