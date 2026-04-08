package com.healthcare.clinic.services.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    @Async
    public void sendAppointmentNotification(String email, String patientName) {
        log.info("Starting async email task for: {}", patientName);
        try {
            // Simulate a slow email server delay
            Thread.sleep(5000);
            log.info("Email successfully sent to {}", email);
        } catch (InterruptedException e) {
            log.error("Email task interrupted", e);
        }
    }
}
