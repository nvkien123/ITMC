package com.blog.itmc.controllers;

import com.blog.itmc.apis.APIResponse;
import com.blog.itmc.apis.DataRequestLogin;
import com.blog.itmc.models.Account;
import com.blog.itmc.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public APIResponse register(@RequestBody Account account) {
        return accountService.register(account);
    }
    @PostMapping("/login")
    public APIResponse login(@RequestBody DataRequestLogin account) {
        return accountService.login(account);
    }
    @PutMapping("/update/{id}")
    public APIResponse update(@PathVariable("id") int id, @RequestBody Account account) {
        return accountService.update(id, account);
    }
}
