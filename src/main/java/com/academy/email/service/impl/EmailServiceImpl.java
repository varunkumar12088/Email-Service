package com.academy.email.service.impl;

import com.academy.email.constant.EmailConstant;
import com.academy.email.domain.Email;
import com.academy.email.domain.Status;
import com.academy.email.dto.EmailDTO;
import com.academy.email.exception.EmailSendException;
import com.academy.email.repository.EmailRepository;
import com.academy.email.service.EmailService;
import com.academy.email.service.EmailTemplateService;
import com.academy.email.service.ValidationService;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private ValidationService validationService;
    @Autowired
    private EmailTemplateService emailTemplateService;


    @Override
    public String sendEmail(EmailDTO emailDTO) {
        LOGGER.info("Sending email to: {}", emailDTO.getTo());
        validationService.isValidRequest(emailDTO);
        // get template by ID or name
        String emailTemplate = emailTemplateService.getEmailTemplateById(emailDTO.getTemplateId());
        LOGGER.debug("Using email template: {}", emailTemplate);
        if(StringUtils.isNotBlank(emailTemplate)) {
            emailDTO.setBody(emailTemplate);
        }
        // Save email to repository
        Email email = emailDTO.toEmail();
        email.setId(UUID.randomUUID().toString());
        email.setStatus(Status.PENDING);
        emailRepository.save(email);

        try {

            String body = getBody(emailDTO.getTemplateVariables(), emailDTO.getBody());
            LOGGER.debug("Processed email body: {}", body);

            MimeMessage message = mailSender.createMimeMessage();
            message.setFrom(emailDTO.getFrom());
            message.setRecipients(MimeMessage.RecipientType.TO, emailDTO.getTo());

            if (!CollectionUtils.isEmpty(emailDTO.getCc())) {
                message.setRecipients(MimeMessage.RecipientType.CC, String.join(EmailConstant.COMMA, emailDTO.getCc()));
            }
            if (!CollectionUtils.isEmpty(emailDTO.getBcc())) {
                message.setRecipients(MimeMessage.RecipientType.BCC, String.join(EmailConstant.COMMA, emailDTO.getBcc()));
            }
            message.setSubject(emailDTO.getSubject());
            message.setText(body, EmailConstant.UTF_8, EmailConstant.HTML);

            mailSender.send(message);
            LOGGER.info("Email sent successfully to: {}", emailDTO.getTo());
            email.setStatus(Status.SENT);
            emailRepository.save(email);
            return "Email sent successfully";
        } catch (Exception e) {
            LOGGER.error("Failed to send email to: {}", emailDTO.getTo(), e);
            email.setStatus(Status.FAILED);
            emailRepository.save(email);
            throw new EmailSendException("Failed to send email to "+ emailDTO.getTo());
        }
    }

    private String getBody(Map<String, Object> map, String template){
        if (StringUtils.isNotBlank(template)) {
           for(Map.Entry<String, Object> entry : map.entrySet()){
               if(ObjectUtils.isNotEmpty(entry.getValue())){
                   template = template.replaceAll(
                           Pattern.quote("${" + entry.getKey() + "}"),
                           entry.getValue().toString());
               }
           }
        }
        return template;
    }
}
