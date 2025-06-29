package com.trend_kart.modules.audit.dto;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class AuditLogDTO implements Serializable {
    private String id;
    private String action;
    private String userId;
    private String userName;
    private String entityType;
    private String entityId;
    private Map<String, Object> auditedChanges;
    private LocalDateTime actedAt;
}