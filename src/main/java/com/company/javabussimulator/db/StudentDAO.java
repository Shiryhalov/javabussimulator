package com.company.javabussimulator.db;

import java.sql.SQLException;
import java.util.List;

import com.company.javabussimulator.entities.Student;

public interface StudentDAO {
    public void addStudent(Student student) throws SQLException;

    public void updateStudent(Student student) throws SQLException;

    public Student getStudentById(Long id) throws SQLException;

    public List<Student> getAllStudents() throws SQLException;

    public void deleteStudent(Student student) throws SQLException;
}
