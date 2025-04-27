package com.hyuse.chatbot.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationErrorResponse extends RuntimeException {

    public ValidationErrorResponse(int value, String erroDeValidação, LocalDateTime now, Map<String, String> errors) {
    }
}
