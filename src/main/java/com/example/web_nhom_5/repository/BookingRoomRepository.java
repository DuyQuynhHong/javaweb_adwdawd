package com.example.web_nhom_5.repository;

import com.example.web_nhom_5.entity.BookingRoomEntity;
import com.example.web_nhom_5.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRoomRepository extends JpaRepository<BookingRoomEntity, Long> {
    List<BookingRoomEntity> findAllByUser_Id(Long userId);
    List<BookingRoomEntity> findAllByStatus(BookingStatus status);
    List<BookingRoomEntity> findAllByRoom_Id(Long roomId);
    List<BookingRoomEntity> findAllByPaid(boolean paid);
    List<BookingRoomEntity> findAllByStatusAndCreatedAtBeforeAndPaid(BookingStatus status, LocalDateTime createdAt, boolean paid);
    long countByRoom_IdAndCheckInBeforeAndCheckOutAfterAndStatusNot(Long roomId, LocalDate checkOut, LocalDate checkIn, BookingStatus status);
}
