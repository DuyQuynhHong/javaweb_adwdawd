package com.example.web_nhom_5.dto.request;


import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size(min = 5,message = "Username must have at least 5 characters")
    String userName;

    @Size(min = 8, message = "Password must have at least 8 characters")
    String password;

    String email;

    String fullName;

}

