package com.example.web_nhom_5.controller.admin;


import com.example.web_nhom_5.dto.api.ApiResponse;
import com.example.web_nhom_5.dto.response.BookingRoomResponse;
import com.example.web_nhom_5.dto.response.BookingServiceResponse;
import com.example.web_nhom_5.enums.BookingStatus;
import com.example.web_nhom_5.service.BookingRoomService;
import com.example.web_nhom_5.service.BookingServiceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/admin/bookings")
@Slf4j
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminBookingController {
    BookingRoomService bookingRoomService;
    BookingServiceService bookingServiceService;


    // lay tat ca cac don dat phong
    @GetMapping(value = {"/rooms","/rooms/"})
    public ApiResponse<List<BookingRoomResponse>> getAllBookingRooms() {
        return ApiResponse.<List<BookingRoomResponse>>builder()
                .result(bookingRoomService.getAllBookingRooms())
                .build();
    }

    // lay tat ca cac don dat phong theo trang thai dat phong(Pending,Confirmed,Cancelled)
    @GetMapping(value = "/rooms/by-status")
    public ApiResponse<List<BookingRoomResponse>> getBookingRoomByStatus(@RequestParam BookingStatus status) {
        return ApiResponse.<List<BookingRoomResponse>>builder()
                .result(bookingRoomService.getAllBookingRoomsByStatus(status))
                .build();
    }

    // cap nhat trang thai dat phong
    // co the dung ham nay de huy don,xac nhan don,...
    @PutMapping(value = "/rooms/{bookingRoomId}/status")
    public ApiResponse<BookingStatus> updateBookingStatusByBookingId(@PathVariable long bookingRoomId, @RequestParam BookingStatus status) {
        return ApiResponse.<BookingStatus>builder()
                .result(bookingRoomService.updateBookingStatusByBookingRoomId(bookingRoomId,status))
                .message("Status updated successfully")
                .build();
    }

    //lay tat ca cac don dat phong theo trang thai thanh toan
    @GetMapping(value = "/rooms/by-paid")
    public ApiResponse<List<BookingRoomResponse>> getBookingRoomByPaid(@RequestParam boolean paid) {
        return ApiResponse.<List<BookingRoomResponse>>builder()
                .result(bookingRoomService.getAllBookingRoomsByPaid(paid))
                .build();
    }


    // SERVICE //

    // lay tat ca cac don dat dich vu
    @GetMapping(value = {"/services","/services/"})
    public ApiResponse<List<BookingServiceResponse>> getAllBookingServices() {
        return ApiResponse.<List<BookingServiceResponse>>builder()
                .result(bookingServiceService.getAllBookingServiceList())
                .build();
    }

    // lay tat ca cac don dat dich vu theo trang thai.
    @GetMapping(value = "/services/by-status")
    public ApiResponse<List<BookingServiceResponse>> getAllBookingsByStatus(@RequestParam BookingStatus status) {
        List<BookingServiceResponse> bookings = bookingServiceService.getAllBookingServiceByStatus(status);
        return ApiResponse.<List<BookingServiceResponse>>builder()
                .result(bookings)
                .build();
    }

    // cap nhat trang thai dat dich vu
    @PutMapping("/services/{bookingServiceId}/status")
    public ApiResponse<BookingStatus> updateBookingStatusByBookingId(@PathVariable Long bookingServiceId, @RequestParam BookingStatus status) {
        return ApiResponse.<BookingStatus>builder()
                .result(bookingServiceService.updateBookingStatusById(bookingServiceId, status))
                .message("Status updated successfully")
                .build();
    }

    // lay danh sac cac don dich vu theo trang thai thanh toan
    @GetMapping(value = "/services/by-paid")
    public ApiResponse<List<BookingServiceResponse>> getAllBookingServiceByPaid(@RequestParam boolean paid) { //true false
        return ApiResponse.<List<BookingServiceResponse>>builder()
                .result(bookingServiceService.getAllBookingServicesByPaid(paid))
                .build();
    }
}
