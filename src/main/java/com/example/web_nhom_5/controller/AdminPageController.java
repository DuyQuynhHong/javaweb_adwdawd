package com.example.web_nhom_5.controller;

import com.example.web_nhom_5.dto.request.RoomCreateRequestDTO;
import com.example.web_nhom_5.dto.response.LocationResponse;
import com.example.web_nhom_5.dto.response.RoomResponse;
import com.example.web_nhom_5.exception.WebException;
import com.example.web_nhom_5.service.LocationService;
import com.example.web_nhom_5.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private LocationService locationService;

    // Hiển thị form thêm mới phòng
    @GetMapping("/room/add")
    public String showAddRoomForm(Model model) {
        List<LocationResponse> locations = locationService.getAllLocation(); // Lấy danh sách các địa điểm
        model.addAttribute("locations", locations);
        model.addAttribute("room", new RoomCreateRequestDTO());
        return "admin/add-room";
    }

    // Xử lý thêm mới phòng
    @PostMapping("/room/add")
    public String addRoom(@Valid @ModelAttribute("room") RoomCreateRequestDTO roomRequest, Model model) {
        try {
            RoomResponse newRoom = roomService.addRoom(roomRequest);
            model.addAttribute("success", "Room added successfully: " + newRoom.getRoomName());
            return "redirect:/admin/room/add"; // Chuyển hướng lại form thêm phòng
        } catch (WebException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "An unexpected error occurred.");
            e.printStackTrace();
        }

        // Nếu có lỗi, vẫn hiển thị lại form và các lựa chọn địa điểm
        List<LocationResponse> locations = locationService.getAllLocation();
        model.addAttribute("locations", locations);
        return "admin/add-room";
    }

    @GetMapping("/header")
    public String showHeader(Model model) {
        return "admin/header";
    }
}
