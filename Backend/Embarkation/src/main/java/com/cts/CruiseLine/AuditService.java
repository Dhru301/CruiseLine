package com.cts.CruiseLine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
    private static final Logger log = LoggerFactory.getLogger(AuditService.class);
    public void record(String action, String entityType) {
        log.info("[AUDIT] action={}, entityType={}", action, entityType);
    }
}
