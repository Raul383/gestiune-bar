package com.restaurant.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "Risipa",
    uniqueConstraints = @UniqueConstraint(name = "uk_risipa", columnNames = {"inventar_id", "data_risipa"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Risipa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inventar_id", nullable = false)
    private Inventar inventar;

    @Column(name = "data_risipa", nullable = false)
    private LocalDate dataRisipa;

    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal cantitate = BigDecimal.ZERO;

    @Column(name = "unitate_masura", nullable = false, length = 20)
    private String unitateMasura;

    @Column(length = 255)
    private String motiv;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false)
    private LocalDateTime updatedAt;
}