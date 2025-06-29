package com.trend_kart.modules.audit.service;

import com.trend_kart.modules.audit.dto.AuditLogDTO;
import com.trend_kart.modules.audit.entity.AuditLog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuditLogService {
    public List<AuditLogDTO> listAudits();

    public void saveAuditLog(AuditLog auditLog);
}
