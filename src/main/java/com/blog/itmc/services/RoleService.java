package com.blog.itmc.services;

import com.blog.itmc.models.Role;
import com.blog.itmc.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepo repository;

    public List<Role> getAll() {
        return repository.findAll();
    }
}
