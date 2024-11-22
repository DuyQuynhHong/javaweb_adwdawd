package com.example.web_nhom_5.repository;

import com.example.web_nhom_5.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, String> {
//    Optional<ServiceEntity> findByCodeName(String codeName);
}
