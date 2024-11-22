package com.example.web_nhom_5.controller;

import com.example.web_nhom_5.dto.request.UserCreationRequest;
import com.example.web_nhom_5.dto.response.LocationResponse;
import com.example.web_nhom_5.dto.response.RoomResponse;
import com.example.web_nhom_5.dto.response.ServiceResponse;
import com.example.web_nhom_5.dto.response.UserResponse;
import com.example.web_nhom_5.entity.UserEntity;
import com.example.web_nhom_5.exception.WebException;
import com.example.web_nhom_5.service.LocationService;
import com.example.web_nhom_5.service.RoomService;
import com.example.web_nhom_5.service.ServiceService;
import com.example.web_nhom_5.service.implement.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/public")
public class PublicPageController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping({"/home","/","/home/"})
    public String getHome(Model model) {
        List<RoomResponse> rooms = roomService.getLimitedRooms(); // Lấy 4 phòng
        List<LocationResponse> locations = locationService.getAllLocation();
        List<ServiceResponse> services = serviceService.getAllServices();
        model.addAttribute("rooms", rooms);
        model.addAttribute("services", services);
        model.addAttribute("locations", locations);
        return "home";
    }


    @GetMapping({"/all-rooms","/","/all-rooms/"})
    public String getRooms(Model model) {

        List<RoomResponse> rooms = roomService.getAllRooms();
        List<LocationResponse> locations = locationService.getAllLocation();
        model.addAttribute("rooms", rooms);
        model.addAttribute("locations", locations);
        return "rooms";
    }

    @GetMapping()
    public String getLocation(Model model) {
        List<LocationResponse> locations = locationService.getAllLocation();
        model.addAttribute("locations", locations);
        return "header";
    }

    @GetMapping("/hotel/{locationCode}")
    public String getRoomsByLocation(@PathVariable("locationCode") String locationCode, Model model) {

        List<RoomResponse> rooms = roomService.getAllRoomsByLocationCode(locationCode);
        model.addAttribute("rooms", rooms);

        List<LocationResponse> locations = locationService.getAllLocation();
        model.addAttribute("locations", locations);

        return "rooms";
    }

    @GetMapping({"/login","/","/login/"})
    public String getLogIn(Model model) {
        return "login";
    }

    @GetMapping({"/signup", "/", "/signup/"})
    public String getSignUp(Model model) {
        model.addAttribute("user", new UserCreationRequest());
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@Valid @ModelAttribute("user") UserCreationRequest request, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            // Nếu có lỗi validation, trả về trang signup với thông báo lỗi
            return "signup";
        }

        try {
            UserResponse userResponse = userService.creatUser(request);

            return "redirect:/public/login";
        } catch (WebException e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        } catch (Exception ex) {
            // Nếu có lỗi không mong muốn, in ra log và trả về thông báo lỗi
            ex.printStackTrace();
            model.addAttribute("error", "Unexpected error occurred: " + ex.getMessage());
            return "signup";
        }
    }
}