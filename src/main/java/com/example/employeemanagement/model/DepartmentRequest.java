package com.example.employeemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest {
    private String name;
    private List<Integer> positionIds;
}
