package com.blog.itmc.services;

import com.blog.itmc.exceptions.ResourceNotFoundException;
import com.blog.itmc.models.Media;
import com.blog.itmc.repositories.MediaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {
    @Autowired
    private MediaRepo mediaRepo;

    public List<Media> getAll() {
        return mediaRepo.findAll();
    }

    public Media getById(String name) {
        return mediaRepo.findById(name).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy media: " + name));
    }

    public Media create(Media media) {
        return mediaRepo.save(media);
    }

    public Media update(Media media) {
        Media mediaFound = this.getById(media.getName());
        mediaFound.setType(media.getType() != null ? media.getType() : mediaFound.getType());
        mediaFound.setUrl(media.getUrl() != null ? media.getUrl() : mediaFound.getUrl());
        return mediaRepo.save(mediaFound);
    }

    public String delete(Media media) {
        Media mediaFound = this.getById(media.getName());
        mediaRepo.delete(mediaFound);
        return "Xóa media thành công!";
    }
}
