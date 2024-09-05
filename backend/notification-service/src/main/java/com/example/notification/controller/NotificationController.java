package com.example.notification.controller;

import com.example.notification.model.Notification;
import com.example.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    // Create a new notification
    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification newNotification = notificationService.createNotification(notification);
        return ResponseEntity.ok(newNotification);
    }

    // Get all notifications
    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotification();
        return ResponseEntity.ok(notifications);
    }

    // Get a notification by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {
        return notificationService.getNotificationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Mark a notification as read
    @PutMapping("/{id}/read")
    public ResponseEntity<Notification> markNotificationAsRead(@PathVariable Long id) {
        Notification updatedNotification = notificationService.markAsRead(id);
        return ResponseEntity.ok(updatedNotification);
    }

    // Delete a notification
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }

    // Fetch notifications for a specific user
    @GetMapping("/user")
    public ResponseEntity<List<Notification>> getNotificationsForUser(@RequestParam Long userId) {
        List<Notification> notifications = notificationService.getNotificationsForUser(userId);
        return ResponseEntity.ok(notifications);
    }
}
