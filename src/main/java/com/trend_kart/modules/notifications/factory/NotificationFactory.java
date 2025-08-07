package com.trend_kart.modules.notifications.factory;

import com.trend_kart.modules.notifications.service.NotificationService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NotificationFactory {
    private final Map<String, NotificationService> notificationMap;

    public NotificationFactory(Map<String, NotificationService> notificationMap) {
        this.notificationMap = notificationMap;
    }

    public NotificationService get(final String notificationType) {
        return notificationMap.get(notificationType + "-notification");
    }
}
