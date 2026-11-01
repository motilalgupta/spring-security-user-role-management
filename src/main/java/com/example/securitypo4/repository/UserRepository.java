package com.example.securitypo4.repository;

import com.example.securitypo4.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser,String> {

    Optional<AppUser> findByUsername(String name);
}
