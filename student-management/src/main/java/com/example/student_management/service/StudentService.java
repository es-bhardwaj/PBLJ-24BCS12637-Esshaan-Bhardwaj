package com.example.student_management.service;

import com.example.student_management.entity.Student;
import com.example.student_management.exception.ResourceNotFoundException;
import com.example.student_management.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public Student updateStudent(Long id, Student updatedDetails) {
        Student existingStudent = getStudentById(id);
        existingStudent.setName(updatedDetails.getName());
        existingStudent.setEmail(updatedDetails.getEmail());
        existingStudent.setCourse(updatedDetails.getCourse());
        existingStudent.setAge(updatedDetails.getAge());
        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(Long id) {
        Student existingStudent = getStudentById(id);
        studentRepository.delete(existingStudent);
    }

    public List<Student> getStudentsByCourse(String course) {
        return studentRepository.findByCourseIgnoreCase(course);
    }
}