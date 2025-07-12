package com.academy.email.dto;

import com.academy.email.domain.EmailTemplate;
import lombok.Data;

@Data
public class EmailTemplateDTO {
    private String id;
    private String name;
    private String templateContent;
    private String applicationName;

    public EmailTemplateDTO(String id, String name, String templateContent, String applicationName) {
        this.id = id;
        this.name = name;
        this.templateContent = templateContent;
        this.applicationName = applicationName;
    }

    public EmailTemplateDTO() {
    }

    public EmailTemplate toEmailTemplate() {
        EmailTemplate emailTemplate = EmailTemplate.builder()
                .id(id)
                .name(name)
                .templateContent(templateContent)
                .applicationName(applicationName)
                .build();
        return emailTemplate;
    }
}
