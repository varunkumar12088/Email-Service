package com.academy.email.controller;

import com.academy.email.dto.EmailDTO;
import com.academy.email.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/emails")
public class EmailController {

     private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailDTO emailDTO) {
        logger.info("Received email request: {}", emailDTO);
        String message = emailService.sendEmail(emailDTO);
        return ResponseEntity.ok(message);

    }
}
