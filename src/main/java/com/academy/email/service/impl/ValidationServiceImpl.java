package com.academy.email.service.impl;

import com.academy.common.exception.ArgumentException;
import com.academy.email.constant.EmailConstant;
import com.academy.email.dto.EmailDTO;
import com.academy.email.service.ValidationService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationServiceImpl.class);

    @Override
    public boolean isValidRequest(EmailDTO emailDTO) {
        ArgumentException exception = new ArgumentException("Invalid request");;
        LOGGER.debug("Validating email request: {}", emailDTO);
        if (emailDTO == null) {
            LOGGER.error("Email request is null");
            throw new IllegalArgumentException("Email request cannot be null");
        }

        if (StringUtils.isBlank(emailDTO.getFrom())) {
            emailDTO.setFrom(System.getenv(EmailConstant.APP_EMAIL_USERNAME));
        }
        if (StringUtils.isBlank(emailDTO.getTo())) {
            LOGGER.error("Recipient email address is missing");
            throw new IllegalArgumentException("Recipient email address cannot be empty");
        }
        if (!isValidEmail(emailDTO.getTo())) {
            LOGGER.error("Invalid recipient email address format: {}", emailDTO.getTo());
            exception.addInvalidField(
                "to",
                "Invalid email format. Please provide a valid email address."
            );
        }
        if (StringUtils.isBlank(emailDTO.getSubject())) {
            LOGGER.error("Email subject is missing");
            exception.addInvalidField(
                "subject",
                "Email subject cannot be empty"
            );
        }

        if (StringUtils.isBlank(emailDTO.getTemplateId())) {
           LOGGER.error("Email template ID is missing, using default template");
            exception.addInvalidField(
                    "templateId",
                    "Email template ID is missing, using default template. Please provide a valid template ID."
            );
        }
        if (exception.hasInvalidFields()) {
            LOGGER.error("Validation failed with errors: {}", exception.getInvalidFields());
            throw exception;
        }

        return true;
    }


    private boolean isValidEmail(String email){
        LOGGER.debug("Validating email format");
        if (StringUtils.isBlank(email) || !email.matches(EmailConstant.REGEX)) {
            return false;
        }
        return true;
    }
}
