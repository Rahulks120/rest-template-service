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


    @Value("${service.b.url}")
    private String baseUrl;

    public String getDataFromServiceB() {
        try {
            return restTemplate.getForObject(baseUrl, String.class);
        } catch (RestClientException e) {
            log.error("Error fetching data from Service B: ", e);
            return "Error fetching data";
        }
    }

    public String postDataToServiceB(Student data) {
        try {
            return restTemplate.postForObject(baseUrl, data, String.class);
        } catch (RestClientException e) {
            log.error("Error posting data to Service B: ", e);
            return "Error posting data";
        }
    }

    public void updateResourceOnServiceB(String resourceId, Student data) {
        String url = baseUrl + "/" + resourceId;
        try {
            restTemplate.put(url, data);
        } catch (RestClientException e) {
            log.error("Error updating resource on Service B: ", e);
        }
    }

    public void deleteResourceFromServiceB(String resourceId) {
        String url = baseUrl + "/" + resourceId;
        try {
            restTemplate.delete(url);
        } catch (RestClientException e) {
            log.error("Error deleting resource from Service B: ", e);
        }
    }
}
