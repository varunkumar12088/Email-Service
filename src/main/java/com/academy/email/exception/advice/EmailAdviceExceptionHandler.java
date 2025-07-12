package com.academy.email.exception.advice;

import com.academy.common.domain.ErrorResponse;
import com.academy.email.exception.EmailSendException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmailAdviceExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailAdviceExceptionHandler.class);
    // This class can be used to handle exceptions specific to email operations

    @ExceptionHandler(EmailSendException.class)
    public ResponseEntity<?> handleEmailException(EmailSendException e) {
        LOGGER.error("An error occurred while sending email", e);
        // You can customize the response as needed
        ErrorResponse response = ErrorResponse.builder()
                .errorCode(400)
                .message(e.getMessage())
                .details(e.getMessage())
                .timestamp(java.time.LocalDateTime.now().toString())
                .build();
        // Return a 400 Bad Request response with the error details
        return ResponseEntity.status(400).body(response);
    }

}
