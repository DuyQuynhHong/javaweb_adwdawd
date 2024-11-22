package com.example.web_nhom_5.controller.admin;

import com.example.web_nhom_5.dto.api.ApiResponse;
import com.example.web_nhom_5.dto.request.RoomCreateRequestDTO;
import com.example.web_nhom_5.dto.request.RoomUpdateRequestDTO;
import com.example.web_nhom_5.dto.response.RoomResponse;
import com.example.web_nhom_5.service.RoomService;
import jakarta.validation.Valid;
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
@RequestMapping("/admin/rooms")
@Slf4j
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminRoomController {
    RoomService roomService;

    // loc tat ca cac phong
    @GetMapping
    public ApiResponse<List<RoomResponse>> getAllRooms() {
        return ApiResponse.<List<RoomResponse>>builder()
                .result(roomService.getAllRooms())
                .build();
    }

    // tao them phong
    @PostMapping
    public ApiResponse<RoomResponse> createRoom(@Valid @RequestBody RoomCreateRequestDTO roomCreateRequestDTO) {
        return ApiResponse.<RoomResponse>builder()
                .result(roomService.addRoom(roomCreateRequestDTO))
                .build();
    }

    // cap nhat phong
    @PutMapping(value = "/{roomId}")
    public ApiResponse<RoomResponse> updateRoom(@RequestBody RoomUpdateRequestDTO roomUpdateRequestDTO, @PathVariable("roomId") Long roomId) {
        return ApiResponse.<RoomResponse>builder()
                .result(roomService.updateRoom(roomUpdateRequestDTO, roomId))
                .build();
    }

    // xoa phong
    @DeleteMapping(value = "/{roomId}")
    public ApiResponse<Object> deleteRoom(@PathVariable("roomId") Long roomId) {
        roomService.deleteRoom(roomId);
        return ApiResponse.<Object>builder()
                .message("successfully")
                .build();
    }
}
