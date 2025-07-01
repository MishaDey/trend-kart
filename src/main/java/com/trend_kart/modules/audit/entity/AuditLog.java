package com.trend_kart.modules.audit.entity;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "audit_logs")
@Data
@Builder
public class AuditLog {
    @Id
    private String id;

    private String action;

    @Field(name = "user_id")
    private String userId;

    @Field(name = "user_name")
    private String userName;

    @Field(name = "entity_type")
    private String entityType;

    @Field(name = "entity_id")
    private String entityId;

    @Field(name = "previous_version")
    private Map<String, Object> previousVersion;

    @Field(name = "current_version")
    private Map<String, Object> currentVersion;

    @Field(name = "audited_changes")
    private Map<String, Object> auditedChanges;

    @Field(name = "acted_at")
    private LocalDateTime actedAt;
}
