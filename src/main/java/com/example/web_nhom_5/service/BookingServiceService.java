package com.example.web_nhom_5.service;

import com.example.web_nhom_5.dto.request.BookingServiceCreateRequestDTO;
import com.example.web_nhom_5.dto.response.BookingServiceResponse;
import com.example.web_nhom_5.entity.BookingServiceEntity;
import com.example.web_nhom_5.enums.BookingStatus;

import java.util.List;

public interface BookingServiceService extends BookingCancellationService,ProcessPaymentService{
    BookingServiceEntity getBookingServiceById(long bookingId);
    List<BookingServiceResponse> getAllBookingServiceByStatus(BookingStatus status);
    BookingServiceResponse addBookingService(BookingServiceCreateRequestDTO bookingServiceCreateRequestDTO);
    List<BookingServiceResponse> getAllBookingServiceList();
    BookingStatus getBookingStatusById(long bookingId);
    BookingStatus updateBookingStatusById(long bookingId, BookingStatus newBookingStatus);
    void deleteBookingServiceById(long bookingId);
    List<BookingServiceResponse> getAllBookingServicesByUser();
    List<BookingServiceResponse> getAllBookingServicesByServiceId(String codeName);
    List<BookingServiceResponse> getAllBookingServicesByPaid(boolean paid);
}
