package com.doorknock.features.model.entities;

import com.doorknock.features.common.enums.VisitStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "visits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID visitId;

    @Column(nullable = false)
    private String outcome;

    @Column(nullable = false)
    private String note;

    @Column(nullable = false)
    private Instant visitedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisitStatus visitStatus;

    @Column(name = "user_id", nullable = false)
    private UUID userId;
}
