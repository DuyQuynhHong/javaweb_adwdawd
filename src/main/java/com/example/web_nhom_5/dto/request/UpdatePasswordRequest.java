package com.example.web_nhom_5.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdatePasswordRequest {
    private String oldPassword;
    @Min(value = 8)
    private String newPassword;
    @Min(value = 8)
    private String confirmPassword;
}
