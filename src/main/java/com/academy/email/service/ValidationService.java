package com.academy.email.service;

import com.academy.email.dto.EmailDTO;

public interface ValidationService {

    boolean isValidRequest(EmailDTO emailDTO);
}
