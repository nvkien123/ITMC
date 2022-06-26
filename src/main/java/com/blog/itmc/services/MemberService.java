package com.blog.itmc.services;

import com.blog.itmc.models.Member;
import com.blog.itmc.repositories.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberRepo repository;

    public List<Member> getAll() {
        return repository.findAll();
    }
}
