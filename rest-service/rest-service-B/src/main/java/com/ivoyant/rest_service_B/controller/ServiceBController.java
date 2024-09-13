package com.ivoyant.rest_service_B.controller;

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
        log.info("Data from Service B");
        return new ResponseEntity<>(allData, HttpStatus.OK);
    }

    @PostMapping("/service-b/api/resource")
    public ResponseEntity<String> receiveData(@RequestBody Student data) {
        Student savedStudent = studentService.saveStudent(data);
        return new ResponseEntity<>("Data received: " + savedStudent.getName() + ", " + savedStudent.getAge(), HttpStatus.CREATED);
    }

    @PutMapping("/service-b/api/resource/{id}")
    public ResponseEntity<String> updateResource(@PathVariable Long id, @RequestBody Student data) {
        Student updatedStudent = studentService.updateStudent(id, data);
        if (updatedStudent != null) {
            log.info("Resource with ID {} has been Updated.", id);
            return new ResponseEntity<>("Resource with ID " + id + " updated successfully", HttpStatus.OK);
        } else {
            log.warn("Resource with ID {} not found.", id);
            return new ResponseEntity<>("Resource with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/service-b/api/resource/{id}")
    public ResponseEntity<String> deleteResource(@PathVariable Long id) {
        if (studentService.deleteStudent(id)) {
            log.info("Resource with ID {} has been deleted.", id);
            return new ResponseEntity<>("Resource with ID " + id + " deleted successfully", HttpStatus.OK);
        } else {
            log.warn("Resource with ID {} not found.", id);
            return new ResponseEntity<>("Resource with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
