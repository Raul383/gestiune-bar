package com.restaurant.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Inventar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String denumire;

    @Column(name = "unitate_masura", nullable = false, length = 20)
    private String unitateMasura;

    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal cantitate = BigDecimal.ZERO;

    @Column(name = "stoc_minim", nullable = false, precision = 12, scale = 3)
    private BigDecimal stocMinim = BigDecimal.ZERO;

    @Column(name = "pret_achizitie", precision = 15, scale = 2)
    private BigDecimal pretAchizitie;

    @Column(nullable = false)
    private Boolean deleted = false;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false)
    private LocalDateTime updatedAt;
}