package com.example.web_nhom_5.repository;

import com.example.web_nhom_5.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    List<RoomEntity> findAllByLocation_LocationName(String locationName);
    List<RoomEntity> findAllByLocation_LocationCode(String locationCode);
}
