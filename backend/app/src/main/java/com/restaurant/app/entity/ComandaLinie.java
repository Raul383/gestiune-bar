package com.restaurant.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Comanda_Linii")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComandaLinie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comanda_id", nullable = false)
    private Comanda comanda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "meniu_id", nullable = false)
    private Meniu meniu;

    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal cantitate = BigDecimal.ONE;

    @Column(length = 255)
    private String observatii;
}