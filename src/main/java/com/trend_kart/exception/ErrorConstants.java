package com.trend_kart.exception;

import java.util.Map;

public class ErrorConstants {
    public static final String VALIDATION_ERROR = "Validation Failed";
    public static final String DATA_INTEGRITY_ERROR = "Data Integrity Violation.";
    public static final String DEFAULT_EXCEPTION_MESSAGE = "Something went wrong. Please try again later.";
    public static final Map<String, String> CONSTRAINT_MESSAGE_MAP = Map.of(
      "users_email_key", "Email ID is already registered."
    );
}
