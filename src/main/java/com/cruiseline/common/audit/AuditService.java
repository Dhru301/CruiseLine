package com.cruiseline.common.audit;

import com.cruiseline.modules.auth.entity.AuditLog;
import com.cruiseline.modules.auth.repository.AuditLogRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Records audit-log entries for sensitive actions
 * (bookings, cabin assignments, account transactions).
 */
@Service
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    public void record(String action, String entityType) {
        Long userId = currentUserId();
        auditLogRepository.save(AuditLog.builder()
                .userId(userId)
                .action(action)
                .entityType(entityType)
                .build());
    }

    private Long currentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof com.cruiseline.config.UserPrincipal up) {
            return up.getUserId();
        }
        return null;
    }

    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

}
