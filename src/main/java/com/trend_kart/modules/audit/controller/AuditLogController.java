package com.trend_kart.modules.audit.controller;

import com.trend_kart.modules.audit.dto.AuditLogDTO;
import com.trend_kart.modules.audit.service.AuditLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/audit_logs")
public class AuditLogController {
    public final AuditLogService auditLogService;

    AuditLogController(final AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @GetMapping
    public List<AuditLogDTO> getAuditLogs() {
        return auditLogService.listAudits();
    }
}
