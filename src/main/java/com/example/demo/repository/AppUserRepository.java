package com.example.demo.repository;

import com.example.demo.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email); [cite_start]// [cite: 388]
    boolean existsByUsername(String username); [cite_start]// [cite: 392]
    boolean existsByEmail(String email); [cite_start]// [cite: 397]
}