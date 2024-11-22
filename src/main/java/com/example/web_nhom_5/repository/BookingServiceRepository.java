package com.example.web_nhom_5.repository;

import com.example.web_nhom_5.entity.BookingServiceEntity;
import com.example.web_nhom_5.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingServiceRepository extends JpaRepository<BookingServiceEntity,Long> {
    List<BookingServiceEntity> findAllByStatus(BookingStatus status);
    List<BookingServiceEntity> findAllByUser_Id(Long userId);
    List<BookingServiceEntity> findAllByService_CodeName(String codeName);
    List<BookingServiceEntity> findAllByPaid(Boolean paid);
    List<BookingServiceEntity> findAllByStatusAndCreatedAtBeforeAndPaid(BookingStatus status, LocalDateTime createdAt, boolean paid);
}
