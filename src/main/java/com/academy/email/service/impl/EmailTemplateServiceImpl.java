package com.academy.email.service.impl;

import com.academy.email.domain.EmailTemplate;
import com.academy.email.dto.EmailTemplateDTO;
import com.academy.email.exception.ResourceNotFoundException;
import com.academy.email.repository.EmailTemplateRepository;
import com.academy.email.service.EmailTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

     private static final Logger logger = LoggerFactory.getLogger(EmailTemplateServiceImpl.class);

     @Autowired
     private EmailTemplateRepository emailTemplateRepository;

    @Override
    public String createEmailTemplate(EmailTemplateDTO emailTemplateDTO) {
        logger.info("Creating email template with name: {}", emailTemplateDTO.getName());
        EmailTemplate emailTemplate = emailTemplateDTO.toEmailTemplate();
        // Here you would typically save the emailTemplate to a database
        emailTemplateRepository.save(emailTemplate);
        return "Email template created with ID: " + emailTemplate.getId();
    }

    @Override
    public String updateEmailTemplate(String id, EmailTemplateDTO emailTemplateDTO) {
        return null;
    }

    @Override
    public void deleteEmailTemplate(String id) {

    }

    @Override
    public String getEmailTemplateById(String id) {
        logger.info("Fetching email template with ID: {}", id);
        Optional<EmailTemplate> emailTemplate = emailTemplateRepository.findById(id);
        if(emailTemplate.isPresent()) {
            EmailTemplate template = emailTemplate.get();
            return template.getTemplateContent();
        }
        throw new ResourceNotFoundException("Email template not found with ID: " + id);
    }

    @Override
    public String getEmailTemplateByName(String name) {
        return null;
    }

    @Override
    public String getAllEmailTemplates() {
        return null;
    }
}
