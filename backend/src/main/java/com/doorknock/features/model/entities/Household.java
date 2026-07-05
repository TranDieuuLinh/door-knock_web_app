package com.doorknock.features.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "household")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID householdId;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String suburb;

    @Column(nullable = false)
    private String note;

    @Column(nullable = false)
    private String postcode;

    @Column(nullable = false)
    private int phone;

    @Column
    private LocalTime bestTime;

    @Column(nullable = false)
    private String familyName;
}
