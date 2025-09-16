package com.example.formapi.service;

import com.example.formapi.dto.request.ConsultationRequest;

public interface ConsultationNotificationService {
    void sendRegistrationNotification(ConsultationRequest request);
}
