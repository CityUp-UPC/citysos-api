package com.citysos.api.notification.api.rest;

import com.citysos.api.notification.domain.entity.NotificationMessage;
import com.citysos.api.notification.service.FirebaseMessagingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
@AllArgsConstructor
public class NotificationController {

    private final FirebaseMessagingService firebaseMessagingService;

    @PostMapping
    public String sendNotificationByToken(@RequestBody NotificationMessage notificationMessage){
        return firebaseMessagingService.sendNotificationByToken(notificationMessage);
    }
}
