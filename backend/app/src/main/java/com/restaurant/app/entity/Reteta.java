package com.restaurant.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(
    name = "Reteta",
    uniqueConstraints = @UniqueConstraint(name = "uk_reteta", columnNames = {"meniu_id", "inventar_id"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reteta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "meniu_id", nullable = false)
    private Meniu meniu;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inventar_id", nullable = false)
    private Inventar inventar;

    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal cantitate;

    @Column(name = "unitate_masura", nullable = false, length = 20)
    private String unitateMasura;

    @Column(length = 255)
    private String observatii;
}