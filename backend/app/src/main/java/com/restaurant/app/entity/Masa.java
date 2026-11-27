package com.restaurant.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Mese")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Masa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nume;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal latime;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal lungime;

    @Column(name = "coord_x", nullable = false, precision = 8, scale = 2)
    private BigDecimal coordX = BigDecimal.ZERO;

    @Column(name = "coord_y", nullable = false, precision = 8, scale = 2)
    private BigDecimal coordY = BigDecimal.ZERO;

    @Column(nullable = false)
    private Boolean ocupata = false;

    @Column(nullable = false)
    private Boolean deleted = false;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false)
    private LocalDateTime updatedAt;
}