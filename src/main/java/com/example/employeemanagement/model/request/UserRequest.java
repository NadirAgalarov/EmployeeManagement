package com.example.employeemanagement.model.request;

import com.example.employeemanagement.model.RoleDto;
import com.example.employeemanagement.validation.annotations.ValidEmail;
import com.example.employeemanagement.validation.annotations.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String name;
    private String surname;
    @ValidEmail
    private String email;
    private String username;
    @ValidPassword()
    private String password;
    private Set<RoleDto> roleDtos;
}
