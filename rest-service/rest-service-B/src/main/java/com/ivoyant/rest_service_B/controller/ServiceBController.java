package com.ivoyant.rest_service_B.controller;

import com.ivoyant.rest_service_B.exception.ResourceNotFoundException;
import com.ivoyant.rest_service_B.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ServiceBController {

    private final StudentService studentService;

    @Autowired
    public ServiceBController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/service-b/api/resource")
    public ResponseEntity<List<Student>> getResource() {
        List<Student> allData = studentService.getAllStudents();
        log.info("Data retrieved from Service B: {}", allData);
        return new ResponseEntity<>(allData, HttpStatus.OK);
    }

    @PostMapping("/service-b/api/resource")
    public ResponseEntity<ErrorResponse> receiveData(@RequestBody Student data) {
        if (data == null || data.getName() == null || data.getAge() == null) {
            log.warn("Invalid data provided for creation.");
            return new ResponseEntity<>(new ErrorResponse("BAD_REQUEST", "Invalid data provided."), HttpStatus.BAD_REQUEST);
        }
        Student savedStudent = studentService.saveStudent(data);
        log.info("Data received and saved: {}", savedStudent);
        return new ResponseEntity<>(new ErrorResponse("SUCCESS", "Data received: " + savedStudent.getName() + ", " + savedStudent.getAge()), HttpStatus.CREATED);
    }

    @PutMapping("/service-b/api/resource/{id}")
    public ResponseEntity<ErrorResponse> updateResource(@PathVariable Long id, @RequestBody Student data) {
        if (data == null || data.getName() == null || data.getAge() == null) {
            log.warn("Invalid data provided for update.");
            return new ResponseEntity<>(new ErrorResponse("BAD_REQUEST", "Invalid data provided."), HttpStatus.BAD_REQUEST);
        }
        Student updatedStudent = studentService.updateStudent(id, data);
        if (updatedStudent != null) {
            log.info("Resource with ID {} has been updated.", id);
            return new ResponseEntity<>(new ErrorResponse("SUCCESS", "Resource with ID " + id + " updated successfully"), HttpStatus.OK);
        } else {
            log.warn("Resource with ID {} not found.", id);
            throw new ResourceNotFoundException("Resource with ID " + id + " not found");
        }
    }

    @DeleteMapping("/service-b/api/resource/{id}")
    public ResponseEntity<ErrorResponse> deleteResource(@PathVariable Long id) {
        if (studentService.deleteStudent(id)) {
            log.info("Resource with ID {} has been deleted.", id);
            return new ResponseEntity<>(new ErrorResponse("SUCCESS", "Resource with ID " + id + " deleted successfully"), HttpStatus.OK);
        } else {
            log.warn("Resource with ID {} not found.", id);
            throw new ResourceNotFoundException("Resource with ID " + id + " not found");
        }
    }
}
