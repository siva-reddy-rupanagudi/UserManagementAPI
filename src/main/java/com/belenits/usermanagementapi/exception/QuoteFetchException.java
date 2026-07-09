package com.belenits.usermanagementapi.exception;

public class QuoteFetchException extends RuntimeException {
    public QuoteFetchException(String message) {
        super(message);
    }

    public QuoteFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}

