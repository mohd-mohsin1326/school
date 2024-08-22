package com.example.school.service;

import com.example.school.model.Student;
import com.example.school.model.StudentRowMapper;
import com.example.school.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;

@Service
public class StudentH2Service implements StudentRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Student> getStudents() {
        return (ArrayList<Student>) db.query("select * from STUDENT", new StudentRowMapper());
    }

    @Override
    public Student addStudent(Student student) {
        String sqlInsert = "insert into STUDENT(studentName, gender, standard) values (?,?,?)";
        db.update(sqlInsert, student.getStudentName(), student.getGender(), student.getStandard());

        Integer studentId = db.queryForObject("select IDENTITY()", Integer.class);

        String sqlSelect = "select * from STUDENT where studentId = ?";

        return db.queryForObject(sqlSelect, new StudentRowMapper(), studentId);
    }

    @Override
    public String addStudents(ArrayList<Student> students) {
        String sql = "insert into STUDENT(studentName, gender, standard) values (?,?,?)";
        int count = 0;

        for (Student student : students) {
            db.update(sql, student.getStudentName(), student.getGender(), student.getStandard());
            count++;
        }

        return "Successfully added " + count + " students";
    }

    @Override
    public Student getStudentById(int studentId) {
        try {
            return db.queryForObject("select * from STUDENT where studentId = ?", new StudentRowMapper(), studentId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Student updateStudent(int studentId, Student student) {
        if (student.getStudentName() != null) {
            db.update("update STUDENT set studentName = ? where studentId =?", student.getStudentName(), studentId);
        }
        if (student.getGender() != null) {
            db.update("update STUDENT set gender = ? where studentId =?", student.getGender(), studentId);
        }
        if (student.getStandard() != 0) {
            db.update("update STUDENT set standard = ? where studentId =?", student.getStandard(), studentId);
        }
        return getStudentById(studentId);
    }

    @Override
    public void deleteStudent(int studentId) {
        db.update("delete from STUDENT where studentId = ?", studentId);
    }

}
