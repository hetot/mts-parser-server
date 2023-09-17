package com.example.mtstestproject.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tariff_benefit")
public class TariffBenefitEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tariff_id")
    private TariffEntity tariff;
}
