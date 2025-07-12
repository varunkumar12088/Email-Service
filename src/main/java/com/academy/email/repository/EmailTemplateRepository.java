package com.academy.email.repository;

import com.academy.email.domain.EmailTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailTemplateRepository extends MongoRepository<EmailTemplate, String> {
}
