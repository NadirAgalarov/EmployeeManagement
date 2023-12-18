package com.example.employeemanagement.model.response;


public record EmployeeResponse(int id, String name, String surname,String email,
                               int positionId, boolean status){
}
