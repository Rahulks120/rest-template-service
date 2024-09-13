package com.ivoyant.rest_service_A.service;

import com.ivoyant.rest_service_A.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    public String getDataFromServiceB() {
        String url = "http://localhost:8081/service-b/api/resource";
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (RestClientException e) {
            log.error("Error fetching data from Service B: ", e);
            return "Error fetching data";
        }
    }

    public String postDataToServiceB(Student data) {
        String url = "http://localhost:8081/service-b/api/resource";
        try {
            return restTemplate.postForObject(url, data, String.class);
        } catch (RestClientException e) {
            log.error("Error posting data to Service B: ", e);
            return "Error posting data";
        }
    }

    public void updateResourceOnServiceB(String resourceId, Student data) {
        String url = "http://localhost:8081/service-b/api/resource/" + resourceId;
        try {
            restTemplate.put(url, data);
        } catch (RestClientException e) {
            log.error("Error updating resource on Service B: ", e);
        }
    }

    public void deleteResourceFromServiceB(String resourceId) {
        String url = "http://localhost:8081/service-b/api/resource/" + resourceId;
        try {
            restTemplate.delete(url);
        } catch (RestClientException e) {
            log.error("Error deleting resource from Service B: ", e);
        }
    }
}
