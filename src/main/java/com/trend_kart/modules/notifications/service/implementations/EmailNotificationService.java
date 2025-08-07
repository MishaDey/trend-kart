package com.trend_kart.modules.notifications.service.implementations;

import com.trend_kart.modules.notifications.dto.NotificationDTO;
import com.trend_kart.modules.notifications.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("email-notification")
public class EmailNotificationService implements NotificationService {
    @Override
    public void sendNotification(NotificationDTO notificationDTO) {
        log.info("EmailNotificationService::sendNotification::from::{}::to::{}::body::{}",
                notificationDTO.getFrom(),
                notificationDTO.getTo(),
                notificationDTO.getBody());
    }
}
