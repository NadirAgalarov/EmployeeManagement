package com.example.employeemanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "dep_id",referencedColumnName = "id",updatable = true)
    private Department department;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pos_id",referencedColumnName = "id",updatable = true)
    private Position position;

    @Column(name = "status")
    private boolean status;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
