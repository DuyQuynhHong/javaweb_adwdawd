package com.example.web_nhom_5.service;

import com.example.web_nhom_5.dto.request.BookingRoomCreateRequestDTO;
import com.example.web_nhom_5.dto.response.BookingRoomResponse;
import com.example.web_nhom_5.entity.BookingRoomEntity;
import com.example.web_nhom_5.enums.BookingStatus;

import java.time.LocalDate;
import java.util.List;

public interface BookingRoomService extends BookingCancellationService,ProcessPaymentService{
    BookingRoomResponse addBookingRoom(BookingRoomCreateRequestDTO bookingRoomCreateRequestDTO);
    BookingRoomEntity getBookingRoomById(Long bookingRoomId);
    BookingStatus getBookingStatusByBookingRoomId(Long bookingRoomId);
    BookingStatus updateBookingStatusByBookingRoomId(Long bookingRoomId, BookingStatus bookingStatus);
    void deleteBookingRoomById(Long bookingRoomId);
    List<BookingRoomResponse> getAllBookingRooms();
    List<BookingRoomResponse> getAllBookingRoomsByStatus(BookingStatus bookingStatus);
    List<BookingRoomResponse> getAllBookingRoomsByUser();
    List<BookingRoomEntity> getAllBookingRoomsByRoomId(Long roomId);
    boolean bookingRoomIsAvailable(Long roomId,LocalDate checkIn, LocalDate checkOut);
    List<BookingRoomResponse> getAllBookingRoomsByPaid(boolean paid);
}
