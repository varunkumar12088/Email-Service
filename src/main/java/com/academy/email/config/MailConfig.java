package com.academy.email.config;


import com.academy.email.constant.EmailConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    private static final String EMAIL_USERNAME = System.getenv(EmailConstant.APP_EMAIL_USERNAME);
    private static final String EMAIL_PASSWORD = System.getenv(EmailConstant.APP_EMAIL_PASSWORD);



    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(EmailConstant.EMAIL_HOST);
        mailSender.setPort(EmailConstant.EMAIL_PORT);
        mailSender.setUsername(EMAIL_USERNAME);
        mailSender.setPassword(EMAIL_PASSWORD);

        Properties props = mailSender.getJavaMailProperties();
        props.put(EmailConstant.EMAIL_TRANSPORT_PROTOCOL, EmailConstant.EMAIL_PROTOCOL);
        props.put(EmailConstant.EMAIL_SMTP_AUTH, EmailConstant.TRUE);
        props.put(EmailConstant.EMAIL_SMTP_STARTTLS_ENABLE, EmailConstant.TRUE);
        props.put(EmailConstant.EMAIL_DEBUG, EmailConstant.TRUE);

        return mailSender;
    }
}
