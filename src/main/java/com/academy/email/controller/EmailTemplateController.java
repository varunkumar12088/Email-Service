package com.academy.email.controller;

import com.academy.email.constant.EmailConstant;
import com.academy.email.dto.EmailTemplateDTO;
import com.academy.email.service.EmailTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/email-templates")
public class EmailTemplateController {
    // LOgger
     private static final Logger logger = LoggerFactory.getLogger(EmailTemplateController.class);

     @Autowired
     private EmailTemplateService emailTemplateService;

     @RequestMapping(value = "/create", method = RequestMethod.POST)
     public ResponseEntity<?> createEmailTemplate(@RequestBody EmailTemplateDTO emailTemplateDTO) {
         logger.info("Creating email template");
         emailTemplateService.createEmailTemplate(emailTemplateDTO);
         return ResponseEntity.ok("Email template created successfully");
     }


}
