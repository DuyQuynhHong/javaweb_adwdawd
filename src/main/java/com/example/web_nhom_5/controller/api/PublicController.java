package com.example.web_nhom_5.controller.api;


import com.example.web_nhom_5.conventer.RoomMapper;
import com.example.web_nhom_5.conventer.ServiceMapper;
import com.example.web_nhom_5.dto.api.ApiResponse;
import com.example.web_nhom_5.dto.request.*;
import com.example.web_nhom_5.dto.response.*;
import com.example.web_nhom_5.service.BookingRoomService;
import com.example.web_nhom_5.service.RoomService;
import com.example.web_nhom_5.service.ServiceService;
import com.example.web_nhom_5.service.implement.AuthenticationService;
import com.example.web_nhom_5.service.implement.UserService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/public")
@Slf4j
@PreAuthorize("permitAll()")
public class PublicController {
    UserService userService;
    AuthenticationService authenticationService;
    RoomService roomService;
    RoomMapper roomMapper;
    ServiceService serviceService;
    ServiceMapper serviceMapper;
    BookingRoomService bookingRoomService;

    @PostMapping({"/login", "/login/"})
    ApiResponse<AuthenticationResponse> authenticationResponseApiResponse(@RequestBody AuthenticationRequest authenticationRequest) {
        var result = authenticationService.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping({"/introspect", "/introspect/"})
    ApiResponse<IntrospectResponse> introspectResponse(@RequestBody IntrospectRequest introspectRequest)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(introspectRequest);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping({"/create", "/create/"})
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.creatUser(userCreationRequest))
                .build();
    }

    @GetMapping("/list-rooms/{locationCode}")
    public ApiResponse<List<RoomResponse>> getAllRoomByLocationCode(@PathVariable String locationCode) {
        return ApiResponse.<List<RoomResponse>>builder()
                .result(roomService.getAllRoomsByLocationCode(locationCode))
                .build();
    }

    @GetMapping("/list-rooms/{locationCode}/{roomId}")
    public ApiResponse<RoomResponse> getRoomInfoByLocationAndId(@PathVariable("roomId") Long roomId,@PathVariable("locationCode") String locationCode) {
        return ApiResponse.<RoomResponse>builder()
                .result(roomMapper.roomEntityToRoomResponse(roomService.getRoomById(roomId)))
                .build();
    }

    //hien thi trang thai nut dat phong la het phong hay dat phong khi chon ngay.
    @GetMapping("/list-rooms/{locationCode}/{roomId}/availability")
    public ApiResponse<String> checkRoomAvailability(@PathVariable("roomId") Long roomId,
                                                     @RequestParam("checkIn") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate checkIn,
                                                     @RequestParam("checkOut") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate checkOut) {
        boolean isAvailable = bookingRoomService.bookingRoomIsAvailable(roomId, checkIn, checkOut);

        if (isAvailable) {
            return ApiResponse.<String>builder()
                    .result("Available")
                    .build();
        } else {
            return ApiResponse.<String>builder()
                    .result("Not Available")
                    .build();
        }
    }

    // Service APIs
    @GetMapping({"/list-services", "/list-services/"})
    public ApiResponse<List<ServiceResponse>> getAllServices() {
        return ApiResponse.<List<ServiceResponse>>builder()
                .result(serviceService.getAllServices())
                .build();
    }

    @GetMapping("/list-services/{serviceId}")
    public ApiResponse<ServiceResponse> getServiceById(@PathVariable("serviceId") String serviceId) {
        return ApiResponse.<ServiceResponse>builder()
                .result(serviceMapper.serviceEntityToServiceResponse(serviceService.getServiceById(serviceId)))
                .build();
    }

}
