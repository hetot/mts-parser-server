package com.example.mtstestproject.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tariff_characteristic")
public class TariffCharacteristicEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tariff_id")
    private TariffEntity tariff;
}
