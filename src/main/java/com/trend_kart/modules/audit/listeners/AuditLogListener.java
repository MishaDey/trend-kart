package com.trend_kart.modules.audit.listeners;

import com.trend_kart.modules.audit.entity.AuditLog;
import com.trend_kart.modules.audit.event.AuditLogEvent;
import com.trend_kart.modules.audit.service.AuditLogService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class AuditLogListener {
    private final AuditLogService auditLogService;

    public AuditLogListener(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void processAuditEvent(AuditLogEvent event) {
        AuditLog auditLog = event.getAuditLog();
        auditLogService.saveAuditLog(auditLog);
    }
}
