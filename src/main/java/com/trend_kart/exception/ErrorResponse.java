package com.trend_kart.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ErrorResponse {
    private int status;
    private List<String> errors;
    private String message;
    private LocalDateTime timestamp;
}
