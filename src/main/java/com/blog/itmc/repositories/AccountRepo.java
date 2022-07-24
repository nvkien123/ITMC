package com.blog.itmc.repositories;

import com.blog.itmc.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
}
