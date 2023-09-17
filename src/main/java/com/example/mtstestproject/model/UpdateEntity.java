package com.example.mtstestproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "update")
public class UpdateEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "created_at")
    private LocalDateTime createdDate = LocalDateTime.now();

    @OneToMany(mappedBy = "update")
    private List<TariffEntity> tariffs;
}
