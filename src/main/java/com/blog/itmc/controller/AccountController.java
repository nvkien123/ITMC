package com.blog.itmc.controller;

import com.blog.itmc.models.Account;
import com.blog.itmc.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/account")
    public List<Account> findAll() {
        return accountService.getAll();
    }
    @PostMapping("/")
    public ResponseEntity<?> register(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.register(account));
    }
}
