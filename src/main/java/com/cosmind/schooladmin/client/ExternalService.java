package com.cosmind.schooladmin.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalService {
    @Autowired
    RestTemplate restTemplate;
    @Value("${externalschool.url}")
    String schoolServerUrl;
    @Value("${notifications-service.url}")
    String notificationsServiceUrl;

    public String getData() {
        return this.restTemplate.getForObject(schoolServerUrl + "/data", String.class);
    }

    public String getNotifications() {
        return this.restTemplate.getForObject(notificationsServiceUrl + "/notifications", String.class);
    }
}
