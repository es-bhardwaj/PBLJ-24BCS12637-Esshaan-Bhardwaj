package com.example.student_management.controller;

import com.example.student_management.entity.Student;
import com.example.student_management.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 1. POST /api/students - Add a new student
    @PostMapping
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
        Student created = studentService.addStudent(student);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // 2. GET /api/students - Retrieve all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // 3. GET /api/students/{id} - Retrieve student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // 4. PUT /api/students/{id} - Update student details
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    // 5. DELETE /api/students/{id} - Delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // 6. GET /api/students/course/{course} - Retrieve students by course
    @GetMapping("/course/{course}")
    public ResponseEntity<List<Student>> getStudentsByCourse(@PathVariable String course) {
        return ResponseEntity.ok(studentService.getStudentsByCourse(course));
    }
}