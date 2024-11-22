package com.example.web_nhom_5.controller;

import com.example.web_nhom_5.dto.response.RoomResponse;
import com.example.web_nhom_5.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class WebNhom5Controller {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public String getRooms(Model model) {
        // Lấy danh sách phòng từ backend
        List<RoomResponse> rooms = roomService.getAllRooms();

        // Thêm danh sách phòng vào model
        model.addAttribute("rooms", rooms);

        // Trả về view 'rooms.html'
        return "rooms";
    }
}