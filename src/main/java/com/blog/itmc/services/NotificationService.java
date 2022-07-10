package com.blog.itmc.services;

import com.blog.itmc.exceptions.ResourceNotFoundException;
import com.blog.itmc.models.Notification;
import com.blog.itmc.repositories.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepo notificationRepo;
    public List<Notification> getAll() {
        return notificationRepo.findAll();
    }
    public Notification getById(long id) {
        return notificationRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy notification có id: " + id));
    }
    public Notification create(Notification notification) {
        return notificationRepo.save(notification);
    }
    public Notification update(Notification notification) {
        Notification foundNoti = this.getById(notification.getId());
        foundNoti.setContentUrl(notification.getContentUrl() == null ? foundNoti.getContentUrl() : notification.getContentUrl());
        foundNoti.setDate(notification.getDate() == null ? foundNoti.getDate() : notification.getDate());
        foundNoti.setTitle(notification.getTitle() == null ? foundNoti.getTitle() : notification.getTitle());
        return notificationRepo.save(foundNoti);
    }
    public String delete(Notification notification) {
        Notification foundNoti = this.getById(notification.getId());
        notificationRepo.delete(foundNoti);
        return "Xóa notification thành công";
    }
}
