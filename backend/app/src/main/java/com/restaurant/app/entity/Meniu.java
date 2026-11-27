package com.restaurant.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Meniu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meniu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String denumire;

    @Column(columnDefinition = "TEXT")
    private String descriere;

    @Column(length = 100)
    private String categorie;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal pret;

    @Column(name = "cota_tva", nullable = false, precision = 5, scale = 2)
    private BigDecimal cotaTva = new BigDecimal("21.00");

    @Column(name = "unitate_masura", nullable = false, length = 20)
    private String unitateMasura = "buc";

    @Column(nullable = false)
    private Boolean disponibil = true;

    @Column(nullable = false)
    private Boolean deleted = false;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false)
    private LocalDateTime updatedAt;
}