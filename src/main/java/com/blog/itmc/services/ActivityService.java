package com.blog.itmc.services;

import com.blog.itmc.exceptions.ResourceNotFoundException;
import com.blog.itmc.models.Activity;
import com.blog.itmc.repositories.ActivityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepo activityRepo;

    public List<Activity> getAll() {
        return activityRepo.findAll();
    }

    public Activity getById(Long id) {
        return activityRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy activity có id: " + id));
    }

    public Activity create(Activity activity) {
        return activityRepo.save(activity);
    }

    public Activity update(Activity activity) {
        Activity activityFound = this.getById(activity.getId());
        activityFound.setName(activity.getName() != null ? activity.getName() : activityFound.getName());
        activityFound.setContentUrl(activity.getContentUrl() != null ? activity.getContentUrl() : activityFound.getContentUrl());
        activityFound.setDate(activity.getDate() != null ? activity.getDate() : activityFound.getDate());
        activityFound.setThumbnailUrl(activity.getThumbnailUrl() != null ? activity.getThumbnailUrl() : activityFound.getThumbnailUrl());
        return activityRepo.save(activity);
    }

    public String delete(Activity activity) {
        Activity activityFound = this.getById(activity.getId());
        activityRepo.delete(activityFound);
        return "Xóa activity thành công";
    }
}
