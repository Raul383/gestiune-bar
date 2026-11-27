package com.restaurant.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "Raport_Consum",
    uniqueConstraints = @UniqueConstraint(name = "uk_raport_consum", columnNames = {"inventar_id", "data_consum"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RaportConsum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inventar_id", nullable = false)
    private Inventar inventar;

    @Column(name = "data_consum", nullable = false)
    private LocalDate dataConsum;

    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal cantitate = BigDecimal.ZERO;

    @Column(name = "unitate_masura", nullable = false, length = 20)
    private String unitateMasura;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false)
    private LocalDateTime updatedAt;
}