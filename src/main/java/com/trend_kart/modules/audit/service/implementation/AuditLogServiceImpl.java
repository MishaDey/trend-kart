package com.trend_kart.modules.audit.service.implementation;

import com.trend_kart.modules.audit.dto.AuditLogDTO;
import com.trend_kart.modules.audit.entity.AuditLog;
import com.trend_kart.modules.audit.repository.AuditLogRepository;
import com.trend_kart.modules.audit.service.AuditLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class AuditLogServiceImpl implements AuditLogService {
    public final AuditLogRepository auditLogRepository;

    AuditLogServiceImpl(final AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public List<AuditLogDTO> listAudits() {
        List<AuditLog> audits = auditLogRepository.findAll();
        return audits.stream().map(this::auditLogToAuditLogDTO).toList();
    }

    @Override
    @Async // We don't want this action to be done in the primary thread
    public void saveAuditLog(AuditLog auditLog) {
        log.info("Thread::{}", Thread.currentThread().getName());
        auditLog.setAuditedChanges(generateAuditedChanges(auditLog));
        auditLogRepository.save(auditLog);
    }

    public Map<String, Object> generateAuditedChanges(AuditLog auditLog) {
        Map<String, Object> previousVersion = auditLog.getPreviousVersion();
        Map<String, Object> currentVersion = auditLog.getCurrentVersion();
        assert previousVersion!=null && currentVersion!=null;

        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(previousVersion.keySet());
        allKeys.addAll(currentVersion.keySet());
        Map<String, Object> changes = new HashMap<>();

        for (String key : allKeys) {
            Object previousValue = previousVersion.get(key);
            Object currentValue = currentVersion.get(key);

            if (!Objects.equals(previousValue, currentValue)) {
                Map<String, Object> delta = new HashMap<>();
                delta.put("previous_version", previousValue);
                delta.put("current_version", currentValue);
                changes.put(key, delta);
            }
        }
        return changes;
    }

    public AuditLogDTO auditLogToAuditLogDTO(AuditLog auditLog) {
        return AuditLogDTO.builder()
                .id(auditLog.getId())
                .action(auditLog.getAction())
                .actedAt(auditLog.getActedAt())
                .userId(auditLog.getUserId())
                .userName(auditLog.getUserName())
                .entityType(auditLog.getEntityType())
                .entityId(auditLog.getEntityId())
                .auditedChanges(auditLog.getAuditedChanges())
                .build();
    }
}
