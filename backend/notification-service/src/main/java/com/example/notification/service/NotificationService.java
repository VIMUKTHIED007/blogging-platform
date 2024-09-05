package com.example.notification.service;

import com.example.notification.model.Notification;
import com.example.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {


    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService (NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }

    public Notification createNotification(Notification notification){

        notification.setDate(java.time.LocalDateTime.now());
        notification.setReadStatus(false);
        return notificationRepository.save(notification);
    }

    public List<Notification> getAllNotification(){
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(Long id){
        return notificationRepository.findById(id);
    }

    public List<Notification>getNotificationsForUser(Long userId){
        return notificationRepository.findByUserId(userId);
    }

    public Notification markAsRead(Long id){
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if(notificationOptional.isPresent()){
            Notification notification = notificationOptional.get();
            notification.setReadStatus(true);
        }
        throw new RuntimeException("Notification not found with id"+ id);
    }

    public void deleteNotification(Long id){
        notificationRepository.deleteById(id);
    }
}
