package com.blog.itmc.services;

import com.blog.itmc.models.Position;
import com.blog.itmc.repositories.PositionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
    @Autowired
    PositionRepo repository;

    public List<Position> getAll() {
        return repository.findAll();
    }
}
