package com.academy.email.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "email_templates")
@Data
@Builder
public class EmailTemplate {

    private String id;
    private String name;
    private String templateContent;
    private String applicationName;
}
