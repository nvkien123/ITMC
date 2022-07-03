package com.blog.itmc.services;

import com.blog.itmc.exceptions.ResourceNotFoundException;
import com.blog.itmc.models.Account;
import com.blog.itmc.models.Member;
import com.blog.itmc.models.Role;
import com.blog.itmc.repositories.AccountRepo;
import com.blog.itmc.repositories.MemberRepo;
import com.blog.itmc.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private MemberRepo memberRepo;
    @Autowired
    private RoleRepo roleRepo;

    public List<Account> getAll() {
        return accountRepo.findAll();
    }

    public Account getById(String id) throws ResourceNotFoundException, IllegalArgumentException  {
        Account accFound = accountRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Tài khoản: " + id));
        return accFound;
    }

    public Account login(Account account) {
        String username = account.getUsername();
        String password = account.getPassword();
        Account accFound = this.getById(username);
        if (!accFound.getPassword().equals(password))
            throw new IllegalArgumentException("Sai thông tin đăng nhập!");
        return accFound;
    }

    public Account register(Account account) {
        String memId = account.getMemId();
        Member memFound = memberRepo.findById(memId).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy member id: " + memId));
        Optional<Account> accFound = accountRepo.findById(account.getUsername());
        if (accFound.isPresent())
            throw new IllegalArgumentException("Tên tài khoản này đã có sử dụng!");
        Role role = roleRepo.findByName("Blogger").get();
        account.setRoleId(role.getId());
        return accountRepo.save(account);
    }

    public Account update(Account account) {
        String username = account.getUsername();
        Account accFound = this.getById(username);
        accFound.setPassword(account.getPassword() != null ? account.getPassword() : accFound.getPassword());
        if (account.getRoleId() != 0) {
            int roleId = account.getRoleId();
            Role role = roleRepo.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy role!"));
            accFound.setRoleId(roleId);
        }
        return accountRepo.save(accFound);
    }
}
