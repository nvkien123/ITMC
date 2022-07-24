package com.blog.itmc.services;

import com.blog.itmc.apis.APIResponse;
import com.blog.itmc.apis.DataRequestLogin;
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

//    public Account getById(String id) throws ResourceNotFoundException, IllegalArgumentException  {
//        Account accFound = accountRepo.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Tài khoản: " + id));
//        return accFound;
//    }

    public APIResponse login(DataRequestLogin account) {
        APIResponse response = new APIResponse(false, "Đăng nhập không thành công", null);
        String username = account.getUsername();
        String password = account.getPassword();
        if (username.isEmpty() || password.isEmpty()) {
            response.setMessage("Tài khoản hoặc mật khẩu không được trống");
            return response;
        }
        // không có tài khoản
        Optional<Account> accFound = accountRepo.findByUsername(username);
        if (accFound.isEmpty() || !accFound.get().getPassword().equals(password)) {
            response.setMessage("Sai thông tin đăng nhập");
            return response;
        }
        response.setStatus(true);
        response.setMessage("Đăng nhập thành công");
        response.setData(accFound.get());
        return response;
    }

    public APIResponse register(Account account) {
        APIResponse response = new APIResponse(false, "Tạo tài khoản thất bại", null);
        int memId = account.getMemId();
        Optional<Member> memFound = memberRepo.findById(memId);
        if (memFound.isPresent()) {
            response.setMessage("Mã sinh viên đã tồn tại");
            return response;
        }
        Optional<Account> accFound = accountRepo.findByUsername(account.getUsername());
        if (accFound.isPresent()) {
            response.setMessage("Tên tài khoản này đã tồn tại");
            return response;
        }
        if (account.getPassword().isEmpty()) {
            response.setMessage("Mật khẩu không được trống");
            return response;
        }
        Optional<Role> roleFound = roleRepo.findById(account.getRoleId());
        if (roleFound.isEmpty()) {
            response.setMessage("Không tồn tại mã vai trò");
            return response;
        }
        Account _account = accountRepo.save(account);
        if(!_account.equals(account)) {
            return response;
        }
        response.setStatus(true);
        response.setMessage("Tạo tài khoản thành công");
        response.setData(_account);
        return response;
    }

    public APIResponse update(int id, Account account) {
        APIResponse response = new APIResponse(false, "Cập nhật thông tin tài khoản thất bại", null);
        Optional<Account> accFound = accountRepo.findById(id);
        if (accFound.isEmpty()) {
            response.setMessage("Tài khoản không tồn tại");
            return response;
        }
        Account _account = accFound.get();
        _account.setUsername(account.getUsername() == null ? _account.getUsername() : account.getUsername());
        _account.setPassword(account.getPassword() == null ? _account.getPassword() : account.getPassword());
        _account.setMemId(account.getMemId() == 0 ? _account.getMemId() : account.getMemId());

        _account = accountRepo.save(_account);
        response.setStatus(true);
        response.setMessage("Cập nhật tài khoản thành công");
        response.setData(_account);
        return response;
    }
}
