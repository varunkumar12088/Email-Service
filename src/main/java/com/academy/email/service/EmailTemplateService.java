package com.academy.email.service;

import com.academy.email.dto.EmailTemplateDTO;

public interface EmailTemplateService {

    String createEmailTemplate(EmailTemplateDTO emailTemplateDTO);

    String updateEmailTemplate(String id, EmailTemplateDTO emailTemplateDTO);

    void deleteEmailTemplate(String id);

    String getEmailTemplateById(String id);

    String getEmailTemplateByName(String name);

    String getAllEmailTemplates();
}
