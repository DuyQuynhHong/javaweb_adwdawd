package com.example.web_nhom_5.repository;

import com.example.web_nhom_5.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUserName(String userName);

    Optional<UserEntity> findByUserName(String UserName);
}
