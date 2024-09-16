package com.ivoyant.rest_service_A.controller;

import com.ivoyant.rest_service_A.entity.Student;
import com.ivoyant.rest_service_A.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/service-a/data")
    public String getData() {
        return apiService.getDataFromServiceB();
    }

    @PostMapping("/send-data")
    public String sendDataToApiB(@RequestBody Student data) {
        return apiService.postDataToServiceB(data);
    }
    @PutMapping("/update-data/{id}")
    public void updateDataOnApiB(@PathVariable String id, @RequestBody Student data) {
        apiService.updateResourceOnServiceB(id, data);
    }

    @DeleteMapping("/delete-data/{id}")
    public void deleteDataFromApiB(@PathVariable String id) {
        apiService.deleteResourceFromServiceB(id);
    }
}
