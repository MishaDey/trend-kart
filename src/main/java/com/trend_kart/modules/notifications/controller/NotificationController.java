package com.trend_kart.modules.notifications.controller;

import com.trend_kart.modules.notifications.dto.NotificationDTO;
import com.trend_kart.modules.notifications.factory.NotificationFactory;
import com.trend_kart.modules.notifications.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    private final NotificationFactory notificationFactory;

    public NotificationController(NotificationFactory notificationFactory) {
        this.notificationFactory = notificationFactory;
    }

    @PostMapping("/notify")
    public ResponseEntity sendNotification(@RequestBody NotificationDTO notificationDTO) {
        NotificationService notificationService = notificationFactory.get(notificationDTO.getType());
        notificationService.sendNotification(notificationDTO);
        return ResponseEntity.ok("Notification Request Received.");
    }
}
