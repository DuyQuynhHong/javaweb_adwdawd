package com.example.web_nhom_5.controller.user;


import com.example.web_nhom_5.dto.api.ApiResponse;
import com.example.web_nhom_5.dto.request.*;
import com.example.web_nhom_5.dto.response.BookingRoomResponse;
import com.example.web_nhom_5.dto.response.BookingServiceResponse;
import com.example.web_nhom_5.dto.response.ProcessPaymentResponse;
import com.example.web_nhom_5.dto.response.UserResponse;
import com.example.web_nhom_5.service.BookingRoomService;
import com.example.web_nhom_5.service.BookingServiceService;
import com.example.web_nhom_5.service.implement.AuthenticationService;
import com.example.web_nhom_5.service.implement.UserService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
@Slf4j
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {

    UserService userService;
    BookingServiceService bookingServiceService;
    BookingRoomService bookingRoomService;
    private final AuthenticationService authenticationService;

    // cac ham trong day da tu dong lay id cua nguoi dang nhap nen chi can truyen id cua room,service,..

    // thong tin nguoi dung
    @GetMapping(value = {"/myInfo","/myInfo/"})
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PostMapping({"/myInfo","/myInfo/"})
    ApiResponse<UserResponse> updateMyInfo(@RequestBody UserProfileUpdateRequest userUpdateRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUserProfile(userUpdateRequest))
                .build();
    }

    // thay doi mat khau
    @PutMapping(value = {"/myInfo/change-pass","/myInfo/change-pass/"})
    ApiResponse<String> updateUserPassword(@RequestBody UpdatePasswordRequest request) {
        return ApiResponse.<String>builder()
                .result(userService.updatePassword(request))
                .build();
    }

    // dat phong
    @PostMapping(value = {"/booking-rooms","/booking-rooms/"})
    public ApiResponse<BookingRoomResponse> createBookingRoom(@Valid @RequestBody BookingRoomCreateRequestDTO bookingRoomCreateRequestDTO) {
        return ApiResponse.<BookingRoomResponse>builder()
                .result(bookingRoomService.addBookingRoom(bookingRoomCreateRequestDTO))
                .build();
    }

    // lay danh sach phong da dat
    @GetMapping(value = {"/myInfo/booking-rooms","/myInfo/booking-rooms/"})
    public ApiResponse<List<BookingRoomResponse>> getAllBookingRoomByUser() {
        return ApiResponse.<List<BookingRoomResponse>>builder()
                .result(bookingRoomService.getAllBookingRoomsByUser())
                .build();
    }

    // thanh toan phong
    @PostMapping(value = "/booking-rooms/{bookingRoomId}/payment")
    public ApiResponse<ProcessPaymentResponse> doRoomProcessPayment(@PathVariable long bookingRoomId, @RequestParam long amount) {
        return ApiResponse.<ProcessPaymentResponse>builder()
                .result(bookingRoomService.processPayment(bookingRoomId,amount))
                .build();
    }


    // dat dich vu
    @PostMapping(value = {"/booking-services","/booking-services/"})
    public ApiResponse<BookingServiceResponse> createBookingService(@Valid @RequestBody BookingServiceCreateRequestDTO bookingServiceCreateRequestDTO) {
        return ApiResponse.<BookingServiceResponse>builder()
                .result(bookingServiceService.addBookingService(bookingServiceCreateRequestDTO))
                .build();
    }

    // lay danh sach dich vu da dat
    @GetMapping(value = {"/myInfo/booking-services","/myInfo/booking-services/"})
    public ApiResponse<List<BookingServiceResponse>> getAllBookingServicesByUser() {
        return ApiResponse.<List<BookingServiceResponse>>builder()
                .result(bookingServiceService.getAllBookingServicesByUser())
                .build();
    }

    // thanh toan dich vu
    @PostMapping(value = "/booking-services/{bookingServiceId}/payment")
    public ApiResponse<ProcessPaymentResponse> doServiceProcessPayment(@PathVariable long bookingServiceId, @RequestParam long amount) {
        return ApiResponse.<ProcessPaymentResponse>builder()
                .result(bookingServiceService.processPayment(bookingServiceId, amount))
                .build();
    }

    // dang xuat tk user trong trang user, admin cung co the dang xuat trong nay.
    @PostMapping({"/logout","/logout/"})
    ApiResponse<Void> logout(@RequestBody LogoutRequest logoutRequest)
            throws ParseException, JOSEException {
        authenticationService.logout(logoutRequest);
        return ApiResponse.<Void>builder()
                .build();
    }
}
