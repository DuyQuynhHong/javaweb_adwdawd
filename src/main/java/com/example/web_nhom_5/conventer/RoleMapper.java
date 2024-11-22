package com.example.web_nhom_5.conventer;

import com.example.web_nhom_5.dto.request.RoleRequest;
import com.example.web_nhom_5.dto.response.RoleResponse;
import com.example.web_nhom_5.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface RoleMapper {
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
