package com.academy.email.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document(collection = "emails")
@Builder
public class Email {
    @Id
    private String id;
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
    private Status status; // e.g., SENT, FAILED, PENDING

}
