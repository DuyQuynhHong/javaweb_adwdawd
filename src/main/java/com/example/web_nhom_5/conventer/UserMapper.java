package com.example.web_nhom_5.conventer;

import com.example.web_nhom_5.dto.request.UserCreationRequest;
import com.example.web_nhom_5.dto.request.UserProfileUpdateRequest;
import com.example.web_nhom_5.dto.request.UserUpdateRequest;
import com.example.web_nhom_5.dto.response.UserResponse;
import com.example.web_nhom_5.entity.UserEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserEntity toUser(UserCreationRequest request);
    UserResponse toUserResponse(UserEntity users);

    @Mapping(target = "roles" , ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateUser(@MappingTarget UserEntity users, UserUpdateRequest request);


    @Mapping(target = "roles" , ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "userName", ignore = true)
    void updateProfile(@MappingTarget UserEntity users, UserProfileUpdateRequest request);
}
