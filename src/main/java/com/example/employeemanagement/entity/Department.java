package com.example.employeemanagement.entity;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.boot.model.source.spi.ForeignKeyContributingSource;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private  String name;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY,
            cascade ={CascadeType.MERGE,CascadeType.PERSIST}, targetEntity = Position.class)
    private Set<Position> positions=new HashSet<>();

    @PreRemove
    public void preRremove(){
        for(Position position: positions)
            position.setDepartment(null);
    }
}
