package com.blog.itmc.controllers;

import com.blog.itmc.models.Notification;
import com.blog.itmc.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(notificationService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id) {
        return ResponseEntity.ok(notificationService.getById(id));
    }
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.create(notification));
    }
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.update(notification));
    }
    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.delete(notification));
    }
}
