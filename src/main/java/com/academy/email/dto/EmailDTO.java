package com.academy.email.dto;

import com.academy.email.domain.Email;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class EmailDTO {

    private String to;
    private String subject;
    private String body;
    private String from;
    private List<String> cc;
    private List<String> bcc;
    private String attachmentName;
    private String attachmentContentType;
    private byte[] attachmentData;
    private Map<String, Object> templateVariables;
    private String applicationName;

    public Email toEmail() {
         Email email = Email.builder()
                 .to(to)
                 .subject(subject)
                 .body(body)
                 .from(from)
                 .cc(cc)
                 .bcc(bcc)
                 .attachmentName(attachmentName)
                 .attachmentContentType(attachmentContentType)
                 .attachmentData(attachmentData)
                 .templateVariables(templateVariables)
                 .applicationName(applicationName)
                 .build();
        return email;
    }

}
