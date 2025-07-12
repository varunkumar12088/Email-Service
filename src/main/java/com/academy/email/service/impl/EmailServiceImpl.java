package com.academy.email.service.impl;

import com.academy.email.constant.EmailConstant;
import com.academy.email.domain.Email;
import com.academy.email.domain.Status;
import com.academy.email.dto.EmailDTO;
import com.academy.email.exception.EmailSendException;
import com.academy.email.repository.EmailRepository;
import com.academy.email.service.EmailService;
import com.academy.email.service.ValidationService;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.UUID;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    @Qualifier("stringTemplateEngine")
    private TemplateEngine templateEngine;
    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private ValidationService validationService;


    @Override
    public String sendEmail(EmailDTO emailDTO) {
        LOGGER.info("Sending email to: {}", emailDTO.getTo());
        validationService.isValidRequest(emailDTO);
        // Save email to repository
        Email email = emailDTO.toEmail();
        email.setId(UUID.randomUUID().toString());
        email.setStatus(Status.PENDING);
        emailRepository.save(email);

        try {
            Context context = new Context();
            context.setVariables(emailDTO.getTemplateVariables());
            String body = templateEngine.process(emailDTO.getBody(), context);

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
}
