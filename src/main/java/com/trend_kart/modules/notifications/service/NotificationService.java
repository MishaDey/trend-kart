package com.trend_kart.modules.notifications.service;

import com.trend_kart.modules.notifications.dto.NotificationDTO;

public interface NotificationService {
    public void sendNotification(NotificationDTO notificationDTO);
}
