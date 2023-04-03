package com.example.usm.tickets_project.service;

import java.util.List;

public interface EmailService {
    void sendSimpleEmail(List<String> addresses);
}
