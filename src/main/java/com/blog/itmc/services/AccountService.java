package com.blog.itmc.services;

import com.blog.itmc.exceptions.ResourceNotFoundException;
import com.blog.itmc.models.Account;
import com.blog.itmc.repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepo repository;

    public List<Account> getAll() {
        return repository.findAll();
    }

    public Account getById(String id) throws ResourceNotFoundException, IllegalArgumentException  {
        Account accFound = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Tài khoản: " + id));
        return accFound;
    }
}
