package com.blog.itmc.controllers;

import com.blog.itmc.models.Media;
import com.blog.itmc.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/media")
public class MediaController {
    @Autowired
    private MediaService mediaService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(mediaService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Media media) {
        return ResponseEntity.ok(mediaService.create(media));
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Media media) {
        return ResponseEntity.ok(mediaService.update(media));
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestBody Media media) {
        return ResponseEntity.ok(mediaService.delete(media));
    }
}
