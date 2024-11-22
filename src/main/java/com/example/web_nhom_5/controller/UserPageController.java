package com.example.web_nhom_5.controller;

import com.example.web_nhom_5.dto.request.UserProfileUpdateRequest;
import com.example.web_nhom_5.dto.response.BookingRoomResponse;
import com.example.web_nhom_5.dto.response.UserResponse;
import com.example.web_nhom_5.service.BookingRoomService;
import com.example.web_nhom_5.service.implement.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasRole('USER')")
public class UserPageController {
    @Autowired
    private UserService userService;

    @GetMapping("/myInfo")
    public String getMyInfo(Model model) {
        UserResponse user = userService.getMyInfo();
        model.addAttribute("user", user);
        return "my_info";
    }

    @PostMapping("/myInfo")
    public String updateMyInfo(@ModelAttribute("user") UserProfileUpdateRequest userUpdateRequest, Model model) {
        userService.updateUserProfile(userUpdateRequest);
        return "redirect:/users/myInfo";
    }

    @GetMapping("/myInfo/booking-rooms")
    public String getAllBookingRooms(Model model) {
        BookingRoomService bookingRoomService = null;
        List<BookingRoomResponse> bookedRooms = bookingRoomService.getAllBookingRoomsByUser();
        model.addAttribute("bookedRooms", bookedRooms);
        return "booked_rooms";
    }
}
