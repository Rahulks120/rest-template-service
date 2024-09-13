package com.ivoyant.rest_service_B.service;

import com.ivoyant.rest_service_B.controller.Student;
import com.ivoyant.rest_service_B.exception.ResourceNotFoundException;
import com.ivoyant.rest_service_B.repository.SomeDataObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final SomeDataObjectRepository someDataObjectRepository;

    @Autowired
    public StudentService(SomeDataObjectRepository someDataObjectRepository) {
        this.someDataObjectRepository = someDataObjectRepository;
    }

    public List<Student> getAllStudents() {
        return someDataObjectRepository.findAll();
    }

    public Student saveStudent(Student student) {
        return someDataObjectRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {
        Student existingStudent = someDataObjectRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Resource with ID " + id + " not found"));
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        return someDataObjectRepository.save(existingStudent);
    }

    public boolean deleteStudent(Long id) {
        if (someDataObjectRepository.existsById(id)) {
            someDataObjectRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
