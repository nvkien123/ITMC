package com.blog.itmc.controllers;

import com.blog.itmc.models.Account;
import com.blog.itmc.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("")
    public List<Account> findAll() {
        return accountService.getAll();
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.register(account));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.login(account));
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.update(account));
    }
}
