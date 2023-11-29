package com.example.employeemanagement.repository;

import com.example.employeemanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
