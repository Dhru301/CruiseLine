package com.cts.CruiseLine.service;

import com.cts.CruiseLine.enums.NotificationCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    public void notify(Long userId, String message, NotificationCategory category) {
        log.info("[NOTIFY-STUB] user={}, category={}, message={}", userId, category, message);
    }
}
