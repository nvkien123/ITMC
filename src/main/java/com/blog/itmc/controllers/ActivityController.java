package com.blog.itmc.controllers;

import com.blog.itmc.models.Activity;
import com.blog.itmc.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(activityService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Activity activity) {
        return ResponseEntity.ok(activityService.create(activity));
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Activity activity) {
        return ResponseEntity.ok(activityService.update(activity));
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestBody Activity activity) {
        return ResponseEntity.ok(activityService.delete(activity));
    }
}
