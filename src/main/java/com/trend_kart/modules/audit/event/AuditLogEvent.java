package com.trend_kart.modules.audit.event;

import com.trend_kart.modules.audit.entity.AuditLog;
import org.springframework.context.ApplicationEvent;

public class AuditLogEvent extends ApplicationEvent {
    // transient keyword can be used to exclude a field from serialization
    private final transient AuditLog auditLog;

    public AuditLogEvent(Object source, AuditLog auditLog) {
        super(source);
        this.auditLog = auditLog;
    }

    public AuditLog getAuditLog() {
        return auditLog;
    }
}