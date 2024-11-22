package com.example.web_nhom_5.controller.admin;

import com.example.web_nhom_5.dto.api.ApiResponse;
import com.example.web_nhom_5.dto.request.LogoutRequest;
import com.example.web_nhom_5.dto.request.UserUpdateRequest;
import com.example.web_nhom_5.dto.response.UserResponse;
import com.example.web_nhom_5.service.implement.AuthenticationService;
import com.example.web_nhom_5.service.implement.UserService;
import com.nimbusds.jose.JOSEException;
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
@RequestMapping("/admin/users")
@Slf4j
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminUserController {
    UserService userService;
    private final AuthenticationService authenticationService;

    // lay tat ca nguoi dung
    @GetMapping()
    ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUser())
                .build();
    }

    @DeleteMapping("/{userid}")
    ApiResponse<Object> deleteUser(@PathVariable("userid") Long userid){
        userService.deleteUser(userid);
        return ApiResponse.<Object>builder()
                .message("successfully")
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUserByAdmin(@PathVariable Long userId,@RequestBody UserUpdateRequest userUpdateRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUserByAdmin(userId,userUpdateRequest))
                .build();
    }

    // dang xuat tk admin trong trang admin
    @PostMapping({"/logout","/logout/"})
    ApiResponse<Void> logout(@RequestBody LogoutRequest logoutRequest)
            throws ParseException, JOSEException {
        authenticationService.logout(logoutRequest);
        return ApiResponse.<Void>builder()
                .build();
    }

}
