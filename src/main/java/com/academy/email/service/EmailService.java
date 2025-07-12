package com.academy.email.service;

import com.academy.email.dto.EmailDTO;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    public String sendEmail(EmailDTO emailDTO);
}
