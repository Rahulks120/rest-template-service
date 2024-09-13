package com.ivoyant.rest_service_B.repository;

import com.ivoyant.rest_service_B.controller.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SomeDataObjectRepository extends JpaRepository<Student, Long> {
}