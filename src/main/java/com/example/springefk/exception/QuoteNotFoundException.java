package com.example.springefk.exception;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class QuoteNotFoundException extends Exception {

    public QuoteNotFoundException(String service, String method, String message) {
        super(message);
        log.info(String.format("%s time: %s, method: %s, value: %s, status: %s, message: %s",
                service,
                LocalDateTime.now().toString(),
                method,
                null,
                "ERROR",
                message));

    }

}
